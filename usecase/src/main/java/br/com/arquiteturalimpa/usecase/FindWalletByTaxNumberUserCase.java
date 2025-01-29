package br.com.arquiteturalimpa.usecase;

import br.com.arquiteturalimpa.core.domain.Wallet;
import br.com.arquiteturalimpa.core.exception.NotFoundException;

public interface FindWalletByTaxNumberUserCase {

    Wallet findByTaxNumber(String taxNumber) throws Exception;
}
