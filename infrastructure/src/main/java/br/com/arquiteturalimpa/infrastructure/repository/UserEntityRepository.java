package br.com.arquiteturalimpa.infrastructure.repository;

import br.com.arquiteturalimpa.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {

    Boolean existsByTaxNumber(String TexNumber);

    Boolean existsByEmail(String email);
}
