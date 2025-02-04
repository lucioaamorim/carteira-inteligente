package br.com.arquiteturalimpa.application.usecaseimpl;

import br.com.arquiteturalimpa.usecase.ConsultBalanceUseCase;
import br.com.arquiteturalimpa.usecase.FindWalletByTaxNumberUserCase;

import java.math.BigDecimal;

public class ConsultBalanceUseCaseImpl implements ConsultBalanceUseCase {

    final private FindWalletByTaxNumberUserCase findWalletByTaxNumberUserCase;

    public ConsultBalanceUseCaseImpl(FindWalletByTaxNumberUserCase findWalletByTaxNumberUserCase) {
        this.findWalletByTaxNumberUserCase = findWalletByTaxNumberUserCase;
    }


    @Override
    public BigDecimal consult(String taxNumber) throws Exception {
        var wallet = findWalletByTaxNumberUserCase.findByTaxNumber(taxNumber);
        return wallet.getBalance();
    }
}
