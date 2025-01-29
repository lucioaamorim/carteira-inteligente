package br.com.arquiteturalimpa.application.usecaseimpl;

import br.com.arquiteturalimpa.application.gateway.ConsultBalanceGateway;
import br.com.arquiteturalimpa.core.domain.Wallet;
import br.com.arquiteturalimpa.usecase.ConsultBalanceUseCase;

import java.math.BigDecimal;

public class ConsultBalanceUseCaseImpl implements ConsultBalanceUseCase {

    final private ConsultBalanceGateway consultBalanceGateway;

    public ConsultBalanceUseCaseImpl(ConsultBalanceGateway consultBalanceGateway) {
        this.consultBalanceGateway = consultBalanceGateway;
    }


    @Override
    public BigDecimal consult(Wallet wallet) {
        return consultBalanceGateway.Consult(wallet);
    }
}
