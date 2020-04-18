package com.emrullah.aws.model.transaction;

import com.alibaba.fastjson.annotation.JSONField;
import com.emrullah.aws.model.Account;
import com.emrullah.aws.model.exception.InsufficientBalanceException;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="Transaction")
public abstract class Transaction {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name="amount")
    @JSONField(name="amount")
    private double amount;

    @Column(name="cDate")
    private Date cDate;

    @Column(name="type")
    private String type;

    @Column(name="transactionStatus")
    private String transactionStatus;

    public Transaction(){}

    public Transaction(double amount){
        this.id= UUID.randomUUID();
        this.amount=amount;
        this.cDate=new Date();
        this.type=this.getClass().getSimpleName();
    }

    public abstract void doTransaction(Account account) throws InsufficientBalanceException;

    public Date getDate() {
        return cDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
