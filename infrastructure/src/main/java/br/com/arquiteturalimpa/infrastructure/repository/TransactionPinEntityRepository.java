package br.com.arquiteturalimpa.infrastructure.repository;

import br.com.arquiteturalimpa.infrastructure.entity.TransactionPinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionPinEntityRepository extends JpaRepository<TransactionPinEntity, Long> {
}
