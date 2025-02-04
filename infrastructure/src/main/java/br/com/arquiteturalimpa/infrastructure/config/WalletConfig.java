package br.com.arquiteturalimpa.infrastructure.config;

import br.com.arquiteturalimpa.application.gateway.*;
import br.com.arquiteturalimpa.application.usecaseimpl.*;
import br.com.arquiteturalimpa.core.domain.TransactionPin;
import br.com.arquiteturalimpa.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletConfig {

    @Bean
    public FindWalletByTaxNumberUserCase findWalletByTaxNumberUserCase(FindWalletByTaxNumberGateway findWalletByTaxNumberGateway){
        return new FindWalletByTaxNumberUserCaseImpl(findWalletByTaxNumberGateway);
    }

    @Bean
    public ConsultBalanceUseCase consultBalanceUseCase(FindWalletByTaxNumberUserCase findWalletByTaxNumberUserCase){
        return new ConsultBalanceUseCaseImpl(findWalletByTaxNumberUserCase);
    }

    @Bean
    public TransactionValidateUseCase transactionValidateUseCase(TransactionValidateGateway transactionValidateGateway){
        return new TransactionValidateUseCaseImpl(transactionValidateGateway);
    }

    @Bean
    public CreateTransactionUseCase createTransactionUseCase(CreateTransactionGateway createTransactionGateway){
        return new CreateTransactionUseCaseImpl(createTransactionGateway);
    }

    @Bean
    public UserNotificationUseCase userNotificationUseCase(UserNotificationGateway userNotificationGateway){
        return new UserNotificationUseCaseImpl(userNotificationGateway);
    }

    @Bean
    public UpdateTransactionPinUseCase updateTransactionPinUseCase(){
        return new UpdateTransactionPinUseCase() {
            @Override
            public TransactionPin update(TransactionPin transactionPin) {
                return null;
            }
        };
    }

    @Bean
    public TransactionPinValidateUseCase transactionPinValidateUseCase(TransactionPinValidateGateway transactionPinValidateGateway,
                                                                       UpdateTransactionPinUseCase updateTransactionPinUseCase){
        return new TransactionPinValidateUseCaseImpl(transactionPinValidateGateway, updateTransactionPinUseCase);
    }

    @Bean
    public TransferUseCase transferUseCase(FindWalletByTaxNumberUserCase findWalletByTaxNumberUserCase,
                                           TransferGateway transferGateway, TransactionPinValidateUseCase transactionPinValidateUseCase,
                                           UserNotificationUseCase userNotificationUseCase){
        return new TransferUseCaseImpl(findWalletByTaxNumberUserCase, transferGateway, transactionPinValidateUseCase, userNotificationUseCase);

    }
}
