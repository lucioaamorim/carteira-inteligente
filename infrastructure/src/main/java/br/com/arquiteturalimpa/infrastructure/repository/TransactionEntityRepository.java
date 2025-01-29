package br.com.arquiteturalimpa.infrastructure.repository;

import br.com.arquiteturalimpa.infrastructure.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, Long> {
}
