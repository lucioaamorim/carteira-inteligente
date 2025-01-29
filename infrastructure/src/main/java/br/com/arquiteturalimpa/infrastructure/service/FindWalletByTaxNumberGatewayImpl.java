package br.com.arquiteturalimpa.infrastructure.service;

import br.com.arquiteturalimpa.application.gateway.FindWalletByTaxNumberGateway;
import br.com.arquiteturalimpa.core.domain.Wallet;
import br.com.arquiteturalimpa.infrastructure.mapper.WalletMapper;
import br.com.arquiteturalimpa.infrastructure.repository.WalletEntityRepository;

public class FindWalletByTaxNumberGatewayImpl implements FindWalletByTaxNumberGateway {

    private final WalletEntityRepository walletEntityRepository;
    private final WalletMapper walletMapper;

    public FindWalletByTaxNumberGatewayImpl(WalletEntityRepository walletEntityRepository, WalletMapper walletMapper) {
        this.walletEntityRepository = walletEntityRepository;
        this.walletMapper = walletMapper;
    }

    @Override
    public Wallet findByTaxNumber(String taxNumber) throws Exception {
        return walletMapper.toWallet(walletEntityRepository.findByUserEntityTaxNumber(taxNumber));
    }
}
