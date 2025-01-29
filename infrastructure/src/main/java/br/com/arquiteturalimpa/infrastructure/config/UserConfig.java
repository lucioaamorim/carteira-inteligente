package br.com.arquiteturalimpa.infrastructure.config;

import br.com.arquiteturalimpa.application.gateway.CreateUserGateway;
import br.com.arquiteturalimpa.application.gateway.EmailAvaliableGateway;
import br.com.arquiteturalimpa.application.gateway.TaxNumberAvaliableGateway;
import br.com.arquiteturalimpa.application.usecaseimpl.CreateUserUseCaseImpl;
import br.com.arquiteturalimpa.application.usecaseimpl.EmailAvaliableUseCaseImpl;
import br.com.arquiteturalimpa.application.usecaseimpl.TaxNumberAvaliableUseCaseImpl;
import br.com.arquiteturalimpa.usecase.CreateUserUseCase;
import br.com.arquiteturalimpa.usecase.EmailAvaliableUseCase;
import br.com.arquiteturalimpa.usecase.TaxNumberAvaliableUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public TaxNumberAvaliableUseCaseImpl taxNumberAvaliableUseCase(TaxNumberAvaliableGateway taxNumberAvaliableGateway){
        return new TaxNumberAvaliableUseCaseImpl(taxNumberAvaliableGateway);
    }

    @Bean
    public EmailAvaliableUseCase emailAvaliableUseCase(EmailAvaliableGateway emailAvaliableGateway){
        return new EmailAvaliableUseCaseImpl(emailAvaliableGateway);
    }

    @Bean
    public CreateUserUseCase createUserUseCase(TaxNumberAvaliableUseCase taxNumberAvaliableUseCase,
                                               EmailAvaliableUseCase emailAvaliableUseCase,
                                               CreateUserGateway createUserGateway){
        return new CreateUserUseCaseImpl(taxNumberAvaliableUseCase, emailAvaliableUseCase, createUserGateway);
    }

}
