package br.com.arquiteturalimpa.application.gateway;

import br.com.arquiteturalimpa.core.domain.Transaction;

public interface TransferGateway {

    public Boolean transfer(Transaction transaction);
}
