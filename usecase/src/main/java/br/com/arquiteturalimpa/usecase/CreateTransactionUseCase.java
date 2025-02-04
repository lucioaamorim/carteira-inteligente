package br.com.arquiteturalimpa.usecase;

import br.com.arquiteturalimpa.core.domain.Transaction;
import br.com.arquiteturalimpa.core.domain.Wallet;
import br.com.arquiteturalimpa.core.exception.TransferException;

import java.math.BigDecimal;

public interface CreateTransactionUseCase {

    Transaction create(Wallet to, Wallet from, BigDecimal value) throws TransferException;
}
