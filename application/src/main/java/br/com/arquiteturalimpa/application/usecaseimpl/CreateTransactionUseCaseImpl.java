package br.com.arquiteturalimpa.application.usecaseimpl;

import br.com.arquiteturalimpa.application.gateway.CreateTransactionGateway;
import br.com.arquiteturalimpa.core.domain.Transaction;
import br.com.arquiteturalimpa.core.domain.Wallet;
import br.com.arquiteturalimpa.core.exception.TransferException;
import br.com.arquiteturalimpa.core.exception.enums.ErrorCodeEnum;
import br.com.arquiteturalimpa.usecase.CreateTransactionUseCase;

import java.math.BigDecimal;

public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    final private CreateTransactionGateway createTransactionGateway;

    public CreateTransactionUseCaseImpl(CreateTransactionGateway createTransactionGateway) {
        this.createTransactionGateway = createTransactionGateway;
    }

    @Override
    public Transaction create(Wallet to, Wallet from, BigDecimal value) throws TransferException {
        var transaction = new Transaction(to, from, value);
        var transationSaved = createTransactionGateway.create(transaction);

        if(transationSaved == null){
            throw new TransferException(ErrorCodeEnum.TR0003.getMessage(), ErrorCodeEnum.TR0003.getCode() );
        }

        return transationSaved;
    }
}
