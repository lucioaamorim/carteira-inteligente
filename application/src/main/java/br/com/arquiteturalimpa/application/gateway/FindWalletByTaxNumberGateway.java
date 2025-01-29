package br.com.arquiteturalimpa.application.gateway;

import br.com.arquiteturalimpa.core.domain.Wallet;

public interface FindWalletByTaxNumberGateway {

    public Wallet findByTaxNumber(String taxNumber) throws Exception;
}
