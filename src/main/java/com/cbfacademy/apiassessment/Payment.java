package com.cbfacademy.apiassessment;

import java.math.BigDecimal;
import java.util.UUID;


public class Payment {

    private final UUID id;
    private BigDecimal amount;
    private BigDecimal balance;
    private String cardNumber;
    private String cardHolderName;
    private String cvv;

    public Payment(BigDecimal amount, BigDecimal balance, String cardNumber, String cardHolderName,
            String cvv) {

        this.id = UUID.randomUUID();
        this.amount = amount;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
    }

    public UUID getId() {
        return this.id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardHolderName() {
        return this.cardHolderName;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCvv() {
        return this.cvv;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

}
