package br.com.arquiteturalimpa.usecase;

import br.com.arquiteturalimpa.core.domain.User;
import br.com.arquiteturalimpa.core.exception.EmailException;
import br.com.arquiteturalimpa.core.exception.InternalServerErrorException;
import br.com.arquiteturalimpa.core.exception.TaxNumberException;
import br.com.arquiteturalimpa.core.exception.TransactionPinException;

public interface CreateUserUseCase {
    void create(User user, String pin) throws TaxNumberException, EmailException, InternalServerErrorException, TransactionPinException;
}
