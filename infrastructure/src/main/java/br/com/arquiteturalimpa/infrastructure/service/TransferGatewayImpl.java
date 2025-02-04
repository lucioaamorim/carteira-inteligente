package br.com.arquiteturalimpa.infrastructure.service;

import br.com.arquiteturalimpa.application.gateway.TransferGateway;
import br.com.arquiteturalimpa.core.domain.Transaction;
import br.com.arquiteturalimpa.core.domain.enums.TransactionStatusEnum;
import br.com.arquiteturalimpa.core.exception.TransferException;
import br.com.arquiteturalimpa.infrastructure.mapper.TransactionMapper;
import br.com.arquiteturalimpa.infrastructure.mapper.WalletMapper;
import br.com.arquiteturalimpa.infrastructure.repository.TransactionEntityRepository;
import br.com.arquiteturalimpa.infrastructure.repository.WalletEntityRepository;
import br.com.arquiteturalimpa.usecase.TransactionValidateUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferGatewayImpl implements TransferGateway {

    private final WalletEntityRepository walletEntityRepository;
    private final WalletMapper walletMapper;
    private final TransactionEntityRepository transactionEntityRepository;
    private final TransactionMapper transactionMapper;
    private final TransactionValidateUseCase transactionValidateUseCase;

    public TransferGatewayImpl(WalletEntityRepository walletEntityRepository, WalletMapper walletMapper,
                               TransactionEntityRepository transactionEntityRepository, TransactionMapper transactionMapper,
                               TransactionValidateUseCase transactionValidateUseCase) {
        this.walletEntityRepository = walletEntityRepository;
        this.walletMapper = walletMapper;
        this.transactionEntityRepository = transactionEntityRepository;
        this.transactionMapper = transactionMapper;
        this.transactionValidateUseCase = transactionValidateUseCase;
    }

    @Override
    @Transactional
    public Boolean transfer(Transaction transaction) throws TransferException {
        try {
            transactionValidateUseCase.validate(transaction);
            walletEntityRepository.save(walletMapper.toWalleteEntity(transaction.getFromWallet()));
            walletEntityRepository.save(walletMapper.toWalleteEntity(transaction.getToWallet()));
            transaction.setStatus(TransactionStatusEnum.SUCCESS);
            transactionEntityRepository.save(transactionMapper.toTransactionEntity(transaction));
            return true;
        }catch (Exception e){
            transaction.setStatus(TransactionStatusEnum.CANCELED);
            transactionEntityRepository.save(transactionMapper.toTransactionEntity(transaction));
            return false;
        }
    }
}
