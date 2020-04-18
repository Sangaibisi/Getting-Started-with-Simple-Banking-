package com.emrullah.aws.model.transaction;

import com.emrullah.aws.common.GeneralEnumerationDefinitions;
import com.emrullah.aws.model.Account;
import com.emrullah.aws.model.exception.InsufficientBalanceException;

import javax.persistence.Entity;

@Entity
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(){}

    public WithdrawalTransaction(double value){
        super(value);
    }

    @Override
    public void doTransaction(Account account) throws InsufficientBalanceException {
        account.withdraw(super.getAmount());
        setTransactionStatus(GeneralEnumerationDefinitions.TransactionStatus.OK.getShortCode());

    }
}


