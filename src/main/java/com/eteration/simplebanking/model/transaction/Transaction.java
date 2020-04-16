package com.eteration.simplebanking.model.transaction;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.EtarationException.InsufficientBalanceException;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Transaction")
public abstract class Transaction {

    @Id
    @GeneratedValue
    protected int id;

    @Column(name="value")
    protected double value;

    @Column(name="cDate")
    protected Date cDate;

    @ManyToOne( cascade = CascadeType.ALL )
    protected TransactionStatus transactionStatus;

    public Transaction(double value){
        this.value=value;
        cDate=new Date();
    }

    public abstract void doTransaction(Account account) throws InsufficientBalanceException;

    public Date getDate() {
        return cDate;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
