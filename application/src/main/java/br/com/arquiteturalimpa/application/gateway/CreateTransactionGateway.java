package br.com.arquiteturalimpa.application.gateway;

import br.com.arquiteturalimpa.core.domain.Transaction;

public interface CreateTransactionGateway {

    public Transaction create(Transaction transaction);
}
