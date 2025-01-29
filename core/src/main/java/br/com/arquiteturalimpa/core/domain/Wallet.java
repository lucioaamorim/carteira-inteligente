package br.com.arquiteturalimpa.core.domain;

import br.com.arquiteturalimpa.core.domain.enums.UserTypeEnum;
import br.com.arquiteturalimpa.core.exception.TransferException;
import br.com.arquiteturalimpa.core.exception.enums.ErrorCodeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Wallet {
    private Long id;
    private TransactionPin transactionPin;
    private BigDecimal balance;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public Wallet(Long id, TransactionPin transactionPin, BigDecimal balance, User user, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.transactionPin = transactionPin;
        this.balance = balance;
        this.user = user;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public Wallet(TransactionPin transactionPin, BigDecimal balance, User user) {
        this.transactionPin = transactionPin;
        this.user = user;
        this.balance = balance;
        this.createdAt = LocalDateTime.now();
    }

    public Wallet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionPin getTransactionPin() {
        return transactionPin;
    }

    public void setTransactionPin(TransactionPin transactionPin) {
        this.transactionPin = transactionPin;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void receiveValue(BigDecimal value){
        this.balance.add(value);
    }

    public void transferValue(BigDecimal value) throws TransferException {
        if(this.user.getType() == UserTypeEnum.SHOPKEEPER){
            throw new TransferException(ErrorCodeEnum.TR0001.getCode(),ErrorCodeEnum.TR0001.getMessage());
        }

        if(this.balance.compareTo(value)<0){
            throw new TransferException(ErrorCodeEnum.TR0002.getCode(),ErrorCodeEnum.TR0002.getMessage());
        }

        this.balance.subtract(value);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Wallet wallet)) return false;

        return Objects.equals(getId(), wallet.getId()) && getTransactionPin().equals(wallet.getTransactionPin())
                && getBalance().equals(wallet.getBalance()) && getUser().equals(wallet.getUser())
                && getCreatedAt().equals(wallet.getCreatedAt()) && Objects.equals(getUpdateAt(), wallet.getUpdateAt());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + getTransactionPin().hashCode();
        result = 31 * result + getBalance().hashCode();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getCreatedAt().hashCode();
        result = 31 * result + Objects.hashCode(getUpdateAt());
        return result;
    }
}
