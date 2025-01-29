package br.com.arquiteturalimpa.usecase;

import br.com.arquiteturalimpa.core.domain.Transaction;

public interface UserNotificationUseCase {

    Boolean notificate(Transaction transaction, String email);
}
