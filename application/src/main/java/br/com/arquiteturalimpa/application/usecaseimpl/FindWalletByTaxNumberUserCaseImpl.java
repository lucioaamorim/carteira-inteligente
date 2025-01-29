package br.com.arquiteturalimpa.application.usecaseimpl;

import br.com.arquiteturalimpa.application.gateway.FindWalletByTaxNumberGateway;
import br.com.arquiteturalimpa.core.domain.Wallet;
import br.com.arquiteturalimpa.core.exception.NotFoundException;
import br.com.arquiteturalimpa.core.exception.enums.ErrorCodeEnum;
import br.com.arquiteturalimpa.usecase.FindWalletByTaxNumberUserCase;

public class FindWalletByTaxNumberUserCaseImpl implements FindWalletByTaxNumberUserCase {

    final private FindWalletByTaxNumberGateway findWalletByTaxNumberGateway;

    public FindWalletByTaxNumberUserCaseImpl(FindWalletByTaxNumberGateway findWalletByTaxNumberGateway) {
        this.findWalletByTaxNumberGateway = findWalletByTaxNumberGateway;
    }

    @Override
    public Wallet findByTaxNumber(String taxNumber) throws Exception {
        var wallet = findWalletByTaxNumberGateway.findByTaxNumber(taxNumber);
        if(wallet == null){
            throw new NotFoundException(ErrorCodeEnum.WA0001.getMessage(), ErrorCodeEnum.WA0001.getCode());
        }

        return wallet;

    }
}
