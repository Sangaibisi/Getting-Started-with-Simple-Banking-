package com.eteration.simplebanking.model.transaction;


import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.transaction.Transaction;

import java.util.Date;

// This class is a place holder you can change the complete implementation
public class DepositTransaction extends Transaction {

    public DepositTransaction(double value){
        super(value);
    }

    @Override
    public void doTransaction(Account account) {
        account.deposit(value);
    }
}
