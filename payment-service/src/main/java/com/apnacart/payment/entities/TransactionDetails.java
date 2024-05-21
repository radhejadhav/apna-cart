package com.apnacart.payment.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "transaction_table")
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
    @SequenceGenerator(name = "seq_id", sequenceName = "ID_GENERATOR")
    private Long id;
    private double amount;
    private boolean isCredit;
    private boolean isDebit;
    private Instant transactionDatetime;
    private long senderId;
    private long recipientUserId;
    private Status transactionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id",referencedColumnName = "id")
    @JsonBackReference
    private WalletImpl wallet;

    public TransactionDetails() {
    }

    public TransactionDetails(double amount, boolean isCredit, boolean isDebit, Instant transactionDatetime, long senderId, long recipientUserId, Status transactionStatus, WalletImpl wallet) {
        this.amount = amount;
        this.isCredit = isCredit;
        this.isDebit = isDebit;
        this.transactionDatetime = transactionDatetime;
        this.senderId = senderId;
        this.recipientUserId = recipientUserId;
        this.transactionStatus = transactionStatus;
        this.wallet = wallet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isCredit() {
        return isCredit;
    }

    public void setCredit(boolean credit) {
        isCredit = credit;
    }

    public boolean isDebit() {
        return isDebit;
    }

    public void setDebit(boolean debit) {
        isDebit = debit;
    }

    public Instant getTransactionDatetime() {
        return transactionDatetime;
    }

    public void setTransactionDatetime(Instant transactionDatetime) {
        this.transactionDatetime = transactionDatetime;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getRecipientUserId() {
        return recipientUserId;
    }

    public void setRecipientUserId(long recipientUserId) {
        this.recipientUserId = recipientUserId;
    }

    public Status getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(Status transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
