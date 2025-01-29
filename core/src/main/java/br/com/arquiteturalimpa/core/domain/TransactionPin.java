package br.com.arquiteturalimpa.core.domain;

import br.com.arquiteturalimpa.core.exception.TransactionPinException;
import br.com.arquiteturalimpa.core.exception.enums.ErrorCodeEnum;

import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionPin {
    private Long id;
    private String pin;
    private Integer attempt;
    private Boolean blocked;
    private LocalDateTime createdAt;
    private LocalDateTime updateAT;

    public TransactionPin(Long id, String pin, Integer attempt, Boolean blocked,
                          LocalDateTime createdAt, LocalDateTime updateAT) {
        this.id = id;
        this.pin = pin;
        this.attempt = attempt;
        this.blocked = blocked;
        this.createdAt = createdAt;
        this.updateAT = updateAT;
    }

    public TransactionPin(String pin) throws TransactionPinException {
        setPin(pin);
        this.attempt = 3;
        this.blocked = false;
        this.createdAt = LocalDateTime.now();
    }


    public TransactionPin() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) throws TransactionPinException {
        pinIsValid(pin);
        this.pin = pin;
    }

    private void pinIsValid(String pin) throws TransactionPinException {
        if(pin.length()!=8){
            throw new TransactionPinException(ErrorCodeEnum.TRP0001.getCode(), ErrorCodeEnum.TRP0001.getCode());
        }
    }

    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt() {
        if(this.attempt == 1){
            this.blocked = true;
            this.attempt = 0;
        }else {
            this.attempt = this.attempt - 1;
        }
    }

    public void restaureAttempt(){
        this.attempt = 3;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdateAT() {
        return updateAT;
    }

    public void setUpdateAT(LocalDateTime updateAT) {
        this.updateAT = updateAT;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof TransactionPin that)) return false;

        return Objects.equals(getId(), that.getId()) && getPin().equals(that.getPin())
                && getAttempt().equals(that.getAttempt()) && getBlocked().equals(that.getBlocked())
                && getCreatedAt().equals(that.getCreatedAt()) && Objects.equals(getUpdateAT(), that.getUpdateAT());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + getPin().hashCode();
        result = 31 * result + getAttempt().hashCode();
        result = 31 * result + getBlocked().hashCode();
        result = 31 * result + getCreatedAt().hashCode();
        result = 31 * result + Objects.hashCode(getUpdateAT());
        return result;
    }
}
