package br.com.arquiteturalimpa.usecase;

import br.com.arquiteturalimpa.core.domain.TransactionPin;
import br.com.arquiteturalimpa.core.exception.PinException;

public interface TransactionPinValidateUseCase {

    Boolean validate(TransactionPin transactionPin) throws PinException;
}
