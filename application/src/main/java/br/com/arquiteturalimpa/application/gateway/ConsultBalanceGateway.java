package br.com.arquiteturalimpa.application.gateway;

import br.com.arquiteturalimpa.core.domain.Wallet;

import java.math.BigDecimal;

public interface ConsultBalanceGateway {

    public BigDecimal Consult(Wallet wallet);
}
