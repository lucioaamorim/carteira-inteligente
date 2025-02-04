package br.com.arquiteturalimpa.infrastructure.entity;

import br.com.arquiteturalimpa.core.domain.enums.TransactionStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Transactions")
public class TransactionEntity {

    @Column(name = "Id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "FromWalletId")
    private WalletEntity fromWalletEntity;

    @OneToOne
    @JoinColumn(name = "ToWalletId")
    private WalletEntity toWalletEntity;

    @Column(name = "TransactionValue", nullable = false)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private TransactionStatusEnum status;

    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UpdateAt")
    private LocalDateTime upadateAt;

    public TransactionEntity(WalletEntity fromWalletEntity, WalletEntity toWalletEntity, BigDecimal value,
                             TransactionStatusEnum status, LocalDateTime createdAt, LocalDateTime upadateAt) {
        this.fromWalletEntity = fromWalletEntity;
        this.toWalletEntity = toWalletEntity;
        this.value = value;
        this.status = status;
        this.createdAt = createdAt;
        this.upadateAt = upadateAt;
    }
}
