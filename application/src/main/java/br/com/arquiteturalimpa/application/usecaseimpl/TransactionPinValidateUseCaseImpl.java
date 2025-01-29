package br.com.arquiteturalimpa.application.usecaseimpl;

import br.com.arquiteturalimpa.application.gateway.TransactionPinValidateGateway;
import br.com.arquiteturalimpa.core.domain.TransactionPin;
import br.com.arquiteturalimpa.core.exception.PinException;
import br.com.arquiteturalimpa.core.exception.enums.ErrorCodeEnum;
import br.com.arquiteturalimpa.usecase.TransactionPinValidateUseCase;
import br.com.arquiteturalimpa.usecase.UpdateTransactionPinUseCase;

public class TransactionPinValidateUseCaseImpl implements TransactionPinValidateUseCase {

    final TransactionPinValidateGateway transactionPinValidateGateway;
    private UpdateTransactionPinUseCase updateTransactionPinUseCase;

    public TransactionPinValidateUseCaseImpl(TransactionPinValidateGateway transactionPinValidateGateway) {
        this.transactionPinValidateGateway = transactionPinValidateGateway;
    }

    @Override
    public Boolean validate(TransactionPin transactionPin) throws PinException {

        if(transactionPin.getBlocked()){
            throw new PinException(ErrorCodeEnum.PIN0001.getMessage(), ErrorCodeEnum.PIN0001.getCode());
        }

        if(!transactionPinValidateGateway.validate(transactionPin)){
            transactionPin.setAttempt();
            var transactionPinUpdate = updateTransactionPinUseCase.update(transactionPin);
            throw new PinException(ErrorCodeEnum.PIN0002.getMessage() +
                    ". " + transactionPinUpdate.getAttempt() + " attempt(s) remaining.", ErrorCodeEnum.PIN0002.getCode());
        }

        if(transactionPin.getAttempt() < 3){
            transactionPin.restaureAttempt();
        }   updateTransactionPinUseCase.update(transactionPin);

        return true;
    }
}
