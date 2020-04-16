package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.EtarationException.InsufficientBalanceException;

public interface IAccountService {
    public Account findById(int id);
    public void debit(double value, int id) throws Exception;
    public void credit(double value, int id) throws InsufficientBalanceException;

}
