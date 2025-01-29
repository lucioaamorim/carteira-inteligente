package br.com.arquiteturalimpa.core.exception;

public class AuthenticatelException extends Exception{

    private String code;

    public AuthenticatelException(String message, String code) {
        super(message);
        this.code = code;
    }
}
