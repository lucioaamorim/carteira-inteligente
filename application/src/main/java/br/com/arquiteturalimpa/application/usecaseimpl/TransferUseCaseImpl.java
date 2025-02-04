package br.com.arquiteturalimpa.application.usecaseimpl;

import br.com.arquiteturalimpa.application.gateway.TransferGateway;
import br.com.arquiteturalimpa.core.domain.Transaction;
import br.com.arquiteturalimpa.core.domain.enums.TransactionStatusEnum;
import br.com.arquiteturalimpa.core.exception.*;
import br.com.arquiteturalimpa.core.exception.enums.ErrorCodeEnum;
import br.com.arquiteturalimpa.usecase.*;

public class TransferUseCaseImpl implements TransferUseCase {

    private final FindWalletByTaxNumberUserCase findWalletByTaxNumberUserCase;
    private final TransferGateway transferGateway;
    private final TransactionPinValidateUseCase transactionPinValidateUseCase;
    private final UserNotificationUseCase userNotificationUseCase;


    public TransferUseCaseImpl(FindWalletByTaxNumberUserCase findWalletByTaxNumberUserCase,
                               TransferGateway transferGateway, TransactionPinValidateUseCase transactionPinValidateUseCase,
                               UserNotificationUseCase userNotificationUseCase) {

        this.findWalletByTaxNumberUserCase = findWalletByTaxNumberUserCase;
        this.transferGateway = transferGateway;
        this.transactionPinValidateUseCase = transactionPinValidateUseCase;
        this.userNotificationUseCase = userNotificationUseCase;
    }

    @Override
    public Boolean transfer(Transaction transaction, String pin) throws Exception, TransferException {

        transactionPinValidateUseCase.validate(transaction.getFromWallet().getTransactionPin(), pin);
        var fromWallet = findWalletByTaxNumberUserCase.findByTaxNumber(transaction.getFromWallet().getUser().getTaxNumber().getValue());
        var toWallet = findWalletByTaxNumberUserCase.findByTaxNumber(transaction.getToWallet().getUser().getTaxNumber().getValue());
        transaction.setStatus(TransactionStatusEnum.SUCCESS);
        if (!transferGateway.transfer(transaction)){
            throw new InternalServerErrorException(ErrorCodeEnum.TR0003.getMessage(), ErrorCodeEnum.TR0003.getCode());
        }
        userNotificationUseCase.notificate(transaction,fromWallet.getUser().getEmail());
        return true;
    }
}
