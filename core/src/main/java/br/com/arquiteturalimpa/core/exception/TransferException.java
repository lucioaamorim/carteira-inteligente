package br.com.arquiteturalimpa.core.exception;

public class TransferException extends Throwable {

    private String code;

    public TransferException(String code, String message) {
        super(message);
        this.code = code;
    }
}
