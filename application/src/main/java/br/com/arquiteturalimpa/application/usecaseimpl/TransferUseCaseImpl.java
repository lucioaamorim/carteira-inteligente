package br.com.arquiteturalimpa.application.usecaseimpl;

import br.com.arquiteturalimpa.application.gateway.TransferGateway;
import br.com.arquiteturalimpa.core.domain.Transaction;
import br.com.arquiteturalimpa.core.domain.Wallet;
import br.com.arquiteturalimpa.core.exception.*;
import br.com.arquiteturalimpa.core.exception.enums.ErrorCodeEnum;
import br.com.arquiteturalimpa.usecase.*;

import java.math.BigDecimal;

public class TransferUseCaseImpl implements TransferUseCase {

    final private FindWalletByTaxNumberUserCase findWalletByTaxNumberUserCase;
    final private TransactionValidateUseCase transactionValidateUseCase;
    final private CreateTransactionUseCase createTransactionUseCase;
    final private TransferGateway transferGateway;
    final private UserNotificationUseCase userNotificationUseCase;
    final private TransactionPinValidateUseCase transactionPinValidateUseCase;


    public TransferUseCaseImpl(FindWalletByTaxNumberUserCase findWalletByTaxNumberUserCase,
                               TransactionValidateUseCase transactionValidateUseCase,
                               CreateTransactionUseCase createTransactionUseCase, TransferGateway transferGateway,
                               UserNotificationUseCase userNotificationUseCase,
                               TransactionPinValidateUseCase transactionPinValidateUseCase) {

        this.findWalletByTaxNumberUserCase = findWalletByTaxNumberUserCase;
        this.transactionValidateUseCase = transactionValidateUseCase;
        this.createTransactionUseCase = createTransactionUseCase;
        this.transferGateway = transferGateway;
        this.userNotificationUseCase = userNotificationUseCase;
        this.transactionPinValidateUseCase = transactionPinValidateUseCase;
    }

    @Override
    public Boolean transfer(String fromTaxNumber, String toTaxNumber, BigDecimal value, String pin)
            throws Exception, TransferException {

        var fromWallet = findWalletByTaxNumberUserCase.findByTaxNumber(fromTaxNumber);
        var toWallet = findWalletByTaxNumberUserCase.findByTaxNumber(toTaxNumber);

        transactionPinValidateUseCase.validate(fromWallet.getTransactionPin());

        fromWallet.transferValue(value);
        toWallet.receiveValue(value);

        var transaction = createTransactionUseCase.create(new Transaction(fromWallet,toWallet,value));

        transactionValidateUseCase.validate(transaction);

        if (!transferGateway.transfer(transaction)){
            throw new InternalServerErrorException(ErrorCodeEnum.TR0003.getMessage(), ErrorCodeEnum.TR0003.getCode());
        }

        if(!userNotificationUseCase.notificate(transaction, toWallet.getUser().getEmail())){
            throw new NotificationException(ErrorCodeEnum.NT0001.getMessage(), ErrorCodeEnum.NT0001.getCode());
        }
        return true;
    }
}
