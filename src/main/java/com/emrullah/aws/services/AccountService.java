package com.emrullah.aws.services;

import com.emrullah.aws.dao.IAccountDAO;
import com.emrullah.aws.model.Account;
import com.emrullah.aws.model.exception.InsufficientBalanceException;
import com.emrullah.aws.model.transaction.DepositTransaction;
import com.emrullah.aws.model.transaction.WithdrawalTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AccountService implements IAccountService {

    private IAccountDAO accountDAO;

    @Autowired
    public AccountService(IAccountDAO accountDAO){
        this.accountDAO=accountDAO;
    }

    @Override
    public Account findById(int id) {
        Optional<Account> result = accountDAO.findById(id);
        Account theAccount;
        if (result.isPresent()) {
            theAccount = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Did not find customer id - " + id);
        }
        return theAccount;
    }

    public void debit(double value, int id) throws InsufficientBalanceException {
        Account theAccount = findById(id);
        DepositTransaction trx = new DepositTransaction(value);
        theAccount.post(trx);
        accountDAO.save(theAccount);
    }

    @Override
    public void credit(double value, int id) throws InsufficientBalanceException {
        Account theAccount = findById(id);
        WithdrawalTransaction trx = new WithdrawalTransaction(value);
        theAccount.post(trx);
        accountDAO.save(theAccount);

    }
}
