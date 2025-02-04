package br.com.arquiteturalimpa.infrastructure.service;

import br.com.arquiteturalimpa.application.gateway.TransactionPinValidateGateway;
import br.com.arquiteturalimpa.core.domain.TransactionPin;
import br.com.arquiteturalimpa.infrastructure.repository.TransactionPinEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionPinValidateGatewayImpl implements TransactionPinValidateGateway {

    private final TransactionPinEntityRepository transactionPinEntityRepository;

    public TransactionPinValidateGatewayImpl(TransactionPinEntityRepository transactionPinEntityRepository) {
        this.transactionPinEntityRepository = transactionPinEntityRepository;
    }

    @Override
    public boolean validate(TransactionPin transactionPin, String pin) {
        var transactionPinSaved = transactionPinEntityRepository.findById(transactionPin.getId());
        if(transactionPinSaved.isEmpty()){
            return false;
        }

        if(transactionPinSaved.get().getPin() != pin){
            return false;
        }

        return true;
    }
}
