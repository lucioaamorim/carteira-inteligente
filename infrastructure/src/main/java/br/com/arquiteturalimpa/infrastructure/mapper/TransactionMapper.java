package br.com.arquiteturalimpa.infrastructure.mapper;

import br.com.arquiteturalimpa.core.domain.Transaction;
import br.com.arquiteturalimpa.core.domain.enums.TransactionStatusEnum;
import br.com.arquiteturalimpa.infrastructure.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    private final WalletMapper walletMapper;

    public TransactionMapper(WalletMapper walletMapper) {
        this.walletMapper = walletMapper;
    }

    public TransactionEntity toTransactionEntity(Transaction transaction) {
        return new TransactionEntity(
                transaction.getId(),
                walletMapper.toWalleteEntity(transaction.getFromWallet()),
                walletMapper.toWalleteEntity(transaction.getToWallet()),
                transaction.getValue(),
                transaction.getStatus(),
                transaction.getCreatedAt(),
                transaction.getUpdateAt()
        );
    }

    public TransactionEntity CreateTransactionEntity(Transaction transaction) {
        //chegou como transação criada
        return new TransactionEntity(
                walletMapper.toWalleteEntity(transaction.getFromWallet()),
                walletMapper.toWalleteEntity(transaction.getToWallet()),
                transaction.getValue(),
                TransactionStatusEnum.CREATED,
                transaction.getCreatedAt(),
                transaction.getUpdateAt()
        );
    }

    public Transaction toTransaction(TransactionEntity transactionEntity) throws Exception {
        return new Transaction(
                transactionEntity.getId(),
                walletMapper.toWallet(transactionEntity.getFromWalletEntity()),
                walletMapper.toWallet(transactionEntity.getToWalletEntity()),
                transactionEntity.getValue(),
                transactionEntity.getStatus(),
                transactionEntity.getCreatedAt(),
                transactionEntity.getUpadateAt()
        );
    }
}
