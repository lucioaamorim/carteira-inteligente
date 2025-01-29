package br.com.arquiteturalimpa.application.gateway;

import br.com.arquiteturalimpa.core.domain.TransactionPin;
import br.com.arquiteturalimpa.core.domain.User;
import br.com.arquiteturalimpa.core.domain.Wallet;

public interface CreateUserGateway {

    Boolean create(User user, Wallet wallet);
}
