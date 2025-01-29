package br.com.arquiteturalimpa.application.usecaseimpl;

import br.com.arquiteturalimpa.application.gateway.EmailAvaliableGateway;
import br.com.arquiteturalimpa.usecase.EmailAvaliableUseCase;

public class EmailAvaliableUseCaseImpl implements EmailAvaliableUseCase {

    final private EmailAvaliableGateway emailAvaliableGateway;

    public EmailAvaliableUseCaseImpl(EmailAvaliableGateway emailAvaliableGateway) {
        this.emailAvaliableGateway = emailAvaliableGateway;
    }

    @Override
    public Boolean emailAvaliable(String email) {

        return emailAvaliableGateway.emailAvaliable(email);
    }
}
