package br.com.arquiteturalimpa.infrastructure.service;

import br.com.arquiteturalimpa.application.gateway.CreateUserGateway;
import br.com.arquiteturalimpa.core.domain.User;
import br.com.arquiteturalimpa.core.domain.Wallet;
import br.com.arquiteturalimpa.infrastructure.mapper.TransactionPinMapper;
import br.com.arquiteturalimpa.infrastructure.mapper.UserMapper;
import br.com.arquiteturalimpa.infrastructure.mapper.WalletMapper;
import br.com.arquiteturalimpa.infrastructure.repository.TransactionPinEntityRepository;
import br.com.arquiteturalimpa.infrastructure.repository.UserEntityRepository;
import br.com.arquiteturalimpa.infrastructure.repository.WalletEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.arquiteturalimpa.infrastructure.utils.Utilities.log;

@Service
public class CreateUserGatewayImpl implements CreateUserGateway {


    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;
    private final TransactionPinEntityRepository transactionPinEntityRepository;
    private final TransactionPinMapper transactionPinMapper;
    private final WalletEntityRepository walletEntityRepository;
    private final WalletMapper walletMapper;

    public CreateUserGatewayImpl(UserEntityRepository userEntityRepository, UserMapper userMapper, TransactionPinEntityRepository transactionPinEntityRepository, TransactionPinMapper transactionPinMapper, WalletEntityRepository walletEntityRepository, WalletMapper walletMapper) {
        this.userEntityRepository = userEntityRepository;
        this.userMapper = userMapper;
        this.transactionPinEntityRepository = transactionPinEntityRepository;
        this.transactionPinMapper = transactionPinMapper;
        this.walletEntityRepository = walletEntityRepository;
        this.walletMapper = walletMapper;
    }

    @Override
    @Transactional
    public Boolean create(User user, Wallet wallet) {
        try {
            log.info("Start of user creation::CreateUserGatewayImpl");
            var userSaved = userEntityRepository.save(userMapper.toUserEntity(user));
            var transactionPinSaved = transactionPinEntityRepository.save(
                                transactionPinMapper.toTransactionPinEntity(wallet.getTransactionPin()));
            walletEntityRepository.save(walletMapper.toWalleteEntity(wallet, userSaved, transactionPinSaved));
            log.info("User created successfully::CreateUserGatewayImpl");
            return true;

        }catch (Exception e){
            log.error("An error occurred during user creation::CreateUserGatewayImpl");
            return false;

        }
    }
}
