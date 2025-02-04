package br.com.arquiteturalimpa.usecase;

import br.com.arquiteturalimpa.core.domain.Transaction;
import br.com.arquiteturalimpa.core.domain.User;
import br.com.arquiteturalimpa.core.domain.Wallet;
import br.com.arquiteturalimpa.core.exception.*;

import java.math.BigDecimal;

public interface TransferUseCase {

    Boolean transfer(Transaction transaction, String pin)
            throws Exception, TransferException;
}
