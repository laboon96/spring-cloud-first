package com.techstack.wallet_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "wallets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Wallet {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @NonNull
    private Long userId;
    
    @NonNull
    private Date creationDate;
    
    @NonNull
    private Date lastTransactionDate;
    
    @Column(columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal amount;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Wallet wallet = (Wallet) o;
        return Objects.equals(id, wallet.id) && userId.equals(wallet.userId) && Objects.equals(amount, wallet.amount);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, userId, amount);
    }
}
