package br.com.arquiteturalimpa.usecase;

import br.com.arquiteturalimpa.core.exception.AuthenticatelException;

public interface UserAuthenticateUseCase {

    Boolean authenticate(String username, String password) throws AuthenticatelException;
}
