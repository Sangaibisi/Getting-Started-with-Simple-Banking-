package com.emrullah.aws.model;

import com.emrullah.aws.common.GeneralEnumerationDefinitions;
import com.emrullah.aws.model.exception.InsufficientBalanceException;
import com.emrullah.aws.model.transaction.Transaction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Account")
public class Account {

    /**
     * Unique identifier for database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    /**
     * owner
     * Represent user's account number
     */
    @Column(name="owner")
    private String owner;

    /**
     * accountNumber
     * Represent user's account number
     */
    @Column(name="accountNumber")
    private String accountNumber;

    /**
     * balance
     * Balance must be BigDecimal instead of double
     * Cuz of we need to do calculation.
     */
    @Column(name="balance")
    private BigDecimal balance;

    /**
     * transactions
     * Whole customer's transaction process history
     */
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transaction> transactionsHistory;

    /**
     * no-arg default constructor for Account model
     */
    public Account(){}

    /**
     * @param owner
     * @param accountNumber
     *
     * Trusted package constructor.
     * Trusted simply means when Account initialized, balance always will be 0
     * if balance is 0, val could not be INFLATED.
     */
    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.transactionsHistory=new ArrayList<>();
        this.balance= new BigDecimal(0);
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        try {
            transaction.doTransaction(this);
            transaction.setTransactionStatus(GeneralEnumerationDefinitions.TransactionStatus.OK.getShortCode());
        } catch (InsufficientBalanceException e) {
            transaction.setTransactionStatus(GeneralEnumerationDefinitions.TransactionStatus.ERROR.getShortCode());
            throw e;
        } finally {
            transactionsHistory.add(transaction);
        }
    }

    public void deposit(double d){
        balance=balance.add(BigDecimal.valueOf(d));
    }

    public void withdraw(double d) throws InsufficientBalanceException{
        if(balance.subtract(BigDecimal.valueOf(d)).compareTo(BigDecimal.ZERO) < 0){
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        balance=balance.subtract(BigDecimal.valueOf(d));

    }

    public String getOwner() {
        return owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactionsHistory;
    }
}
