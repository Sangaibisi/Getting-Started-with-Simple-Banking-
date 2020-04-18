package com.emrullah.aws.model.transaction;

import com.emrullah.aws.model.Account;

public class DepositTransaction extends Transaction {

    public DepositTransaction(double value){
        super(value);
    }

    @Override
    public void doTransaction(Account account) {
        account.deposit(super.getAmount());
    }
}
