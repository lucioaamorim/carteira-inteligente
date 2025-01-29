package br.com.arquiteturalimpa.application.gateway;

import br.com.arquiteturalimpa.core.domain.Transaction;

public interface UserNotificationGateway {

    public Boolean notificate(Transaction transaction, String email);

}
