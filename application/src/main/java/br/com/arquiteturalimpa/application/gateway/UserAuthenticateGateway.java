package br.com.arquiteturalimpa.application.gateway;

public interface UserAuthenticateGateway {

    public Boolean authenticate(String username, String password);
}
