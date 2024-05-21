package com.apnacart.payment.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "wallet")
public class WalletImpl implements Wallet{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
    @SequenceGenerator(name = "seq_id", sequenceName = "ID_GENERATOR")
    private Long id;
    private Long userId;
    @Column(name = "amount", columnDefinition = "bigint")
    private Double amount;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<TransactionDetails> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public double credit(Long senderId, Double amount) {
        if(userId==1)throw new RuntimeException("Custom");
        this.amount += amount;
        TransactionDetails transaction =
                new TransactionDetails(
                        amount,
                        true,
                        false,
                        Instant.now(),
                        senderId,
                        this.userId,
                        Status.COMPLETED,
                        this
                );

        this.transactions.add(transaction);
        return this.amount;
    }

    @Override
    public double debit(Long recipientId, Double amount) {
        this.amount -= amount;
        TransactionDetails transaction =
                new TransactionDetails(
                        amount,
                        false,
                        true,
                        Instant.now(),
                        recipientId,
                        this.userId,
                        Status.COMPLETED,
                        this
                );

        this.transactions.add(transaction);
        return this.amount;
    }

    @Override
    public double checkBalance() {
        return this.amount;
    }
}
