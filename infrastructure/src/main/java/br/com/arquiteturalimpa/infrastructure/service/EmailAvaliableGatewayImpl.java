package br.com.arquiteturalimpa.infrastructure.service;

import br.com.arquiteturalimpa.application.gateway.EmailAvaliableGateway;
import br.com.arquiteturalimpa.infrastructure.repository.UserEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailAvaliableGatewayImpl implements EmailAvaliableGateway {

    private final UserEntityRepository userEntityRepository;

    public EmailAvaliableGatewayImpl(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public Boolean emailAvaliable(String email) {
        return !userEntityRepository.existsByEmail(email);
    }
}
