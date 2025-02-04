package br.com.arquiteturalimpa.usecase;

import br.com.arquiteturalimpa.core.domain.Wallet;

import java.math.BigDecimal;

public interface ConsultBalanceUseCase {

    BigDecimal consult(String taxNumber) throws Exception;
}
