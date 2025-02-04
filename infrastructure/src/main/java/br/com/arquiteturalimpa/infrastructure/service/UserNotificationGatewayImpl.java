package br.com.arquiteturalimpa.infrastructure.service;

import br.com.arquiteturalimpa.application.gateway.UserNotificationGateway;
import br.com.arquiteturalimpa.core.domain.Transaction;
import org.springframework.stereotype.Service;

@Service
public class UserNotificationGatewayImpl implements UserNotificationGateway {
    @Override
    public Boolean notificate(Transaction transaction, String email) {
        return true;
    }
}
