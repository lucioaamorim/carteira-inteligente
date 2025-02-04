package br.com.arquiteturalimpa.infrastructure.mapper;

import br.com.arquiteturalimpa.core.domain.Wallet;
import br.com.arquiteturalimpa.infrastructure.entity.TransactionPinEntity;
import br.com.arquiteturalimpa.infrastructure.entity.UserEntity;
import br.com.arquiteturalimpa.infrastructure.entity.WalletEntity;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

    private final TransactionPinMapper transactionPinMapper;
    private final UserMapper userMapper;

    public WalletMapper(TransactionPinMapper transactionPinMapper, UserMapper userMapper) {
        this.transactionPinMapper = transactionPinMapper;
        this.userMapper = userMapper;
    }

    public WalletEntity toWalleteEntity(Wallet wallet, UserEntity userEntity, TransactionPinEntity transactionPinEntity){
        return new WalletEntity(
                wallet.getBalance(),
                userEntity,
                transactionPinEntity,
                wallet.getCreatedAt(),
                wallet.getUpdateAt()
        );
    }

    public WalletEntity toWalleteEntity(Wallet wallet){
        return new WalletEntity(
                wallet.getBalance(),
                userMapper.toUserEntity(wallet.getUser()),
                transactionPinMapper.toTransactionPinEntity(wallet.getTransactionPin()),
                wallet.getCreatedAt(),
                wallet.getUpdateAt()
        );
    }

    public Wallet toWallet(WalletEntity walletEntity) throws Exception {
        if (walletEntity == null){
            return null;
        }
        return new Wallet(
                walletEntity.getId(),
                transactionPinMapper.toTransactionPin(walletEntity.getTransactionPinEntity()),
                walletEntity.getBalance(),
                userMapper.toUser(walletEntity.getUserEntity()),
                walletEntity.getCreatedAt(),
                walletEntity.getUpadateAt()
        );
    }
}
