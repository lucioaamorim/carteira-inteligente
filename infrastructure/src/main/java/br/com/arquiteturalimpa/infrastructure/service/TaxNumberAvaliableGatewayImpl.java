package br.com.arquiteturalimpa.infrastructure.service;

import br.com.arquiteturalimpa.application.gateway.TaxNumberAvaliableGateway;
import br.com.arquiteturalimpa.infrastructure.repository.UserEntityRepository;
import org.springframework.stereotype.Service;

import static br.com.arquiteturalimpa.infrastructure.utils.Utilities.log;

@Service
public class TaxNumberAvaliableGatewayImpl implements TaxNumberAvaliableGateway {

    private final UserEntityRepository userEntityRepository;

    public TaxNumberAvaliableGatewayImpl(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }


    @Override
    public Boolean taxNumberAvaliable(String taxNumber) {
        log.info("Start of TaxNumber availability check::TaxNumberAvaliableGatewayImpl");
        return !userEntityRepository.existsByTaxNumber(taxNumber);
    }
}
