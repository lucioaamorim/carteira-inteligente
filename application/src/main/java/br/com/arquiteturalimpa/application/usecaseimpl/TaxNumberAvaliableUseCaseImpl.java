package br.com.arquiteturalimpa.application.usecaseimpl;

import br.com.arquiteturalimpa.application.gateway.TaxNumberAvaliableGateway;
import br.com.arquiteturalimpa.usecase.TaxNumberAvaliableUseCase;

public class TaxNumberAvaliableUseCaseImpl implements TaxNumberAvaliableUseCase {

    final private TaxNumberAvaliableGateway taxNumberAvaliableGateway;

    public TaxNumberAvaliableUseCaseImpl(TaxNumberAvaliableGateway taxNumberAvaliableGateway) {
        this.taxNumberAvaliableGateway = taxNumberAvaliableGateway;
    }

    @Override
    public Boolean taxNumberAvaliable(String taxNumber) {
        return taxNumberAvaliableGateway.taxNumberAvaliable(taxNumber);
    }
}
