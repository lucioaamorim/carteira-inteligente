package br.com.arquiteturalimpa.application.gateway;

import br.com.arquiteturalimpa.core.domain.Transaction;

public interface TransactionValidateGateway {
    public Boolean validate(Transaction transaction);
}
