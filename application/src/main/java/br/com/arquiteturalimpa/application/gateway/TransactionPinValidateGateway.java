package br.com.arquiteturalimpa.application.gateway;

import br.com.arquiteturalimpa.core.domain.TransactionPin;

public interface TransactionPinValidateGateway {

    public  boolean validate(TransactionPin transactionPin);
}
