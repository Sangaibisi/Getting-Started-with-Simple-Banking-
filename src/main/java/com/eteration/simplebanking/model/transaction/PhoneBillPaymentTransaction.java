package com.eteration.simplebanking.model.transaction;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.EtarationException.InsufficientBalanceException;

public class PhoneBillPaymentTransaction extends Transaction {

    private String providerName;
    private String phoneNumber;

    public PhoneBillPaymentTransaction(String providerName, String phoneNumber, double value){
        super(value);
        this.providerName=providerName;
        this.phoneNumber=phoneNumber;
    }

    @Override
    public void doTransaction(Account account) throws InsufficientBalanceException {
        account.withdraw(value);
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
