package br.com.arquiteturalimpa.infrastructure.service;

import br.com.arquiteturalimpa.application.gateway.CreateTransactionGateway;
import br.com.arquiteturalimpa.core.domain.Transaction;
import br.com.arquiteturalimpa.infrastructure.mapper.TransactionMapper;
import br.com.arquiteturalimpa.infrastructure.repository.TransactionEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTransactionGatewayImpl implements CreateTransactionGateway {

    private final TransactionEntityRepository transactionEntityRepository;
    private final TransactionMapper transactionMapper;

    public CreateTransactionGatewayImpl(TransactionEntityRepository transactionEntityRepository, TransactionMapper transactionMapper) {
        this.transactionEntityRepository = transactionEntityRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public Transaction create(Transaction transaction) {
        try {
            var transactionEntity = transactionMapper.toTransactionEntity(transaction);

            return transactionMapper.toTransaction(transactionEntityRepository.save(transactionEntity));
        }catch (Exception e){
            return null;
        }
    }
}
