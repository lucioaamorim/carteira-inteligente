package br.com.arquiteturalimpa.application.gateway;

import br.com.arquiteturalimpa.core.domain.Transaction;
import br.com.arquiteturalimpa.core.exception.TransferException;

public interface TransferGateway {

    public Boolean transfer(Transaction transaction) throws TransferException;
}
