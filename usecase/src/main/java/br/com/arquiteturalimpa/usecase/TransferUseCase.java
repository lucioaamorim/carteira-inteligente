package br.com.arquiteturalimpa.usecase;

import br.com.arquiteturalimpa.core.domain.Transaction;
import br.com.arquiteturalimpa.core.exception.*;

import java.math.BigDecimal;

public interface TransferUseCase {

    Boolean transfer(String fromTaxNumber, String toTaxNumber, BigDecimal value, String pin)
            throws Exception, TransferException;
}
