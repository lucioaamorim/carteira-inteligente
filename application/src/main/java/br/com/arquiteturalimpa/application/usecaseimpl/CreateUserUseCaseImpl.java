package br.com.arquiteturalimpa.application.usecaseimpl;

import br.com.arquiteturalimpa.application.gateway.CreateUserGateway;
import br.com.arquiteturalimpa.core.domain.TransactionPin;
import br.com.arquiteturalimpa.core.domain.User;
import br.com.arquiteturalimpa.core.domain.Wallet;
import br.com.arquiteturalimpa.core.exception.EmailException;
import br.com.arquiteturalimpa.core.exception.InternalServerErrorException;
import br.com.arquiteturalimpa.core.exception.TaxNumberException;
import br.com.arquiteturalimpa.core.exception.TransactionPinException;
import br.com.arquiteturalimpa.core.exception.enums.ErrorCodeEnum;
import br.com.arquiteturalimpa.usecase.CreateUserUseCase;
import br.com.arquiteturalimpa.usecase.EmailAvaliableUseCase;
import br.com.arquiteturalimpa.usecase.TaxNumberAvaliableUseCase;

import java.math.BigDecimal;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    final private TaxNumberAvaliableUseCase taxNumberAvaliableUseCase;
    final private EmailAvaliableUseCase emailAvaliableUseCase;
    final private CreateUserGateway createUserGateway;


    public CreateUserUseCaseImpl(TaxNumberAvaliableUseCase taxNumberAvaliableUseCase, EmailAvaliableUseCase emailAvaliableUseCase,
                                 CreateUserGateway createUserGateway) {
        this.taxNumberAvaliableUseCase = taxNumberAvaliableUseCase;
        this.emailAvaliableUseCase = emailAvaliableUseCase;
        this.createUserGateway = createUserGateway;
    }

    @Override
    public void create(User user, String pin) throws TaxNumberException, EmailException, InternalServerErrorException, TransactionPinException {
        if(!taxNumberAvaliableUseCase.taxNumberAvaliable(user.getTaxNumber().getValue())){
            throw new TaxNumberException(ErrorCodeEnum.ON0002.getMessage(), ErrorCodeEnum.ON0002.getCode());
        }

        if(!emailAvaliableUseCase.emailAvaliable(user.getEmail())){
            throw new EmailException(ErrorCodeEnum.ON0003.getMessage(), ErrorCodeEnum.ON0003.getCode());
        }

        if(!createUserGateway.create(user, new Wallet(new TransactionPin(pin), BigDecimal.ZERO, user))){
            throw new InternalServerErrorException(ErrorCodeEnum.ON0004.getMessage(), ErrorCodeEnum.ON0004.getCode());
        }

    }
}
