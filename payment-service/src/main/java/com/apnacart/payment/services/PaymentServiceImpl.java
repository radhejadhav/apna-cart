package com.apnacart.payment.services;

import com.apnacart.payment.dto.PaymentDto;
import com.apnacart.payment.entities.Transaction;
import com.apnacart.payment.entities.TransactionDetails;
import com.apnacart.payment.entities.WalletImpl;
import com.apnacart.payment.repositories.TransactionDao;
import com.apnacart.payment.repositories.WalletDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private WalletDao walletDao;
    private TransactionDao transactionDao;

    public PaymentServiceImpl(WalletDao walletDao, TransactionDao transactionDao) {
        this.walletDao = walletDao;
        this.transactionDao = transactionDao;
    }

    @Override
    @Transactional
    public boolean doPayment(PaymentDto paymentDto) {
        try {
            WalletImpl senderWallet = walletDao.findWalletImplByUserId(paymentDto.getSenderId());
            WalletImpl recipientWallet = walletDao.findWalletImplByUserId(paymentDto.getRecipientId());

            if(senderWallet.checkBalance() >= paymentDto.getAmount()){
                senderWallet.debit(paymentDto.getRecipientId(), paymentDto.getAmount());
                walletDao.save(senderWallet);

                recipientWallet.credit(paymentDto.getSenderId(), paymentDto.getAmount());
                walletDao.save(recipientWallet);
                logger.info("Payment success");
            }else {
                logger.warn("low balance!");
                throw new RuntimeException("you don't have enough balance in your wallet!");
            }
        }catch (Exception e){
            logger.error("Exception while making payment"+ e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    @Override
    public List<TransactionDetails> getStatement(Long userId) {
        try {
            WalletImpl userWallet = walletDao.findWalletImplByUserId(userId);
            List<TransactionDetails> transaction =  transactionDao.findTransactionDetailsByWalletId(userWallet.getId());
            logger.info("generated statement for user:"+ userId);
            return transaction;
        }catch (Exception e){
            logger.error("Exception while getting statement of user: "+userId+" "+ e.getMessage());
            throw new RuntimeException("Exception while getting statement of user: "+userId+" "+ e.getMessage());
        }
    }

    @Override
    public Transaction credit(Long userId, double amount) {
        return null;
    }

    @Override
    public Transaction debit(Long userId, double amount) {
        return null;
    }

    @Override
    public double checkBalance(Long userId) {
        try {
            WalletImpl userWallet = walletDao.findWalletImplByUserId(userId);
            logger.info("returning balance for user: "+ userId);
            return userWallet.checkBalance();
        }catch (Exception e){
            logger.error("Exception while checking balance for user: "+ userId);
            throw new RuntimeException("Exception "+ e.getMessage());
        }
    }
}
