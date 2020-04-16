package com.eteration.simplebanking.model.transaction;


import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.EtarationException.InsufficientBalanceException;
import com.eteration.simplebanking.model.transaction.Transaction;

// This class is a place holder you can change the complete implementation
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(double value){
        super(value);

    }

    @Override
    public void doTransaction(Account account) throws InsufficientBalanceException {
        account.withdraw(value);

    }
}


