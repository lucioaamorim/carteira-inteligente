package br.com.arquiteturalimpa.infrastructure.repository;

import br.com.arquiteturalimpa.infrastructure.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletEntityRepository extends JpaRepository<WalletEntity, Long> {
    public WalletEntity findByUserEntityTaxNumber(String taxNumber);
}
