package com.emrullah.aws.services;

import com.emrullah.aws.model.Account;
import com.emrullah.aws.model.exception.InsufficientBalanceException;

public interface IAccountService {
    Account findById(int id);
    void debit(double value, int id) throws Exception;
    void credit(double value, int id) throws InsufficientBalanceException;

}
