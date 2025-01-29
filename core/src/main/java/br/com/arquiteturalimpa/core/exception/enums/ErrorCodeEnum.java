package br.com.arquiteturalimpa.core.exception.enums;

public enum ErrorCodeEnum {

    ON0001("Invalid Tax Number","ON-0001"),
    ON0002("Unavailable Tax Number","ON-0002"),
    ON0003("Unavailable email","ON-0003"),
    ON0004("Error creating user","ON-0004"),

    TR0001("Shopkeeper does not have a transfer function","TR-0001"),
    TR0002("insufficient balance","TR-0002"),
    TR0003("Error while attempting to realize the transfer","TR-0003"),
    TR0004("Transaction not authorized","TR-0004"),
    TRP0001("Invalid PIN","TRP-0001"),

    WA0001("Wallet not found","WA-0001"),

    NT0001("An error occurred while processing the notification","NT-0001"),

    ATH0001("An error occurred during authentication","ATH-0001"),

    PIN0001("Transaction PIN locked","PIN-0001"),

    PIN0002("Incorrect PIN","PIN-0002")
    ;

    private String message;
    private String code;

    ErrorCodeEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
