package br.com.arquiteturalimpa.application.usecaseimpl;

import br.com.arquiteturalimpa.application.gateway.TransactionPinValidateGateway;
import br.com.arquiteturalimpa.core.domain.TransactionPin;
import br.com.arquiteturalimpa.core.exception.PinException;
import br.com.arquiteturalimpa.core.exception.enums.ErrorCodeEnum;
import br.com.arquiteturalimpa.usecase.TransactionPinValidateUseCase;
import br.com.arquiteturalimpa.usecase.UpdateTransactionPinUseCase;

public class TransactionPinValidateUseCaseImpl implements TransactionPinValidateUseCase {

    private final TransactionPinValidateGateway transactionPinValidateGateway;
    private final UpdateTransactionPinUseCase updateTransactionPinUseCase;

    public TransactionPinValidateUseCaseImpl(TransactionPinValidateGateway transactionPinValidateGateway,
                                             UpdateTransactionPinUseCase updateTransactionPinUseCase) {
        this.transactionPinValidateGateway = transactionPinValidateGateway;
        this.updateTransactionPinUseCase = updateTransactionPinUseCase;
    }

    @Override
    public Boolean validate(TransactionPin transactionPin, String pin) throws PinException {

        if(transactionPin.getBlocked()){
            throw new PinException(ErrorCodeEnum.PIN0001.getMessage(), ErrorCodeEnum.PIN0001.getCode());
        }

        if(!transactionPinValidateGateway.validate(transactionPin, pin)){
            transactionPin.setAttempt();
            var transactionPinUpdate = updateTransactionPinUseCase.update(transactionPin);
            throw new PinException(ErrorCodeEnum.PIN0002.getMessage() +
                    ". " + transactionPinUpdate.getAttempt() + " attempt(s) remaining.", ErrorCodeEnum.PIN0002.getCode());
        }

        if(transactionPin.getAttempt() < 3){
            transactionPin.restaureAttempt();
        }
        updateTransactionPinUseCase.update(transactionPin);

        return true;
    }
}
