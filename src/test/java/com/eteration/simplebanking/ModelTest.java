package com.eteration.simplebanking;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.transaction.DepositTransaction;
import com.eteration.simplebanking.model.EtarationException.InsufficientBalanceException;
import com.eteration.simplebanking.model.transaction.PhoneBillPaymentTransaction;
import com.eteration.simplebanking.model.transaction.WithdrawalTransaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ModelTest {
	
	@Test
	public void testCreateAccountAndSetBalance0() {
		Account account = new Account("Kerem Karaca", "17892");
		assertTrue(account.getOwner().equals("Kerem Karaca"));
		assertTrue(account.getAccountNumber().equals("17892"));
		assertTrue(account.getBalance().doubleValue() == 0);
	}

	@Test
	public void testDepositIntoBankAccount() {
		Account account = new Account("Demet Demircan", "9834");
		account.deposit(100);
		assertTrue(account.getBalance().doubleValue() == 100);
	}

	@Test
	public void testWithdrawFromBankAccount() throws InsufficientBalanceException {
		Account account = new Account("Demet Demircan", "9834");
		account.deposit(100);
		assertTrue(account.getBalance().doubleValue() == 100);
		account.withdraw(50);
		assertTrue(account.getBalance().doubleValue() == 50);
	}

	@Test
	public void testWithdrawException() {
		Assertions.assertThrows( InsufficientBalanceException.class, () -> {
			Account account = new Account("Demet Demircan", "9834");
			account.deposit(100);
			account.withdraw(500);
		  });

	}

	@Test
	public void testTransactions() throws InsufficientBalanceException {
		// Create account
		Account account = new Account("Canan Kaya", "1234");
		assertTrue(account.getTransactions().size() == 0);

		// Deposit Transaction
		DepositTransaction depositTrx = new DepositTransaction(100);
		assertTrue(depositTrx.getDate() != null);
		account.post(depositTrx);
		assertTrue(account.getBalance().doubleValue() == 100);
		assertTrue(account.getTransactions().size() == 1);

		// Withdrawal Transaction
		WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction(60);
		assertTrue(withdrawalTrx.getDate() != null);
		account.post(withdrawalTrx);
		assertTrue(account.getBalance().doubleValue() == 40);
		assertTrue(account.getTransactions().size() == 2);
	}

	@Test
	public void testTransactionImplementation() throws InsufficientBalanceException {
		Account account = new Account("Jim", "12345");
		account.post(new DepositTransaction(1000));
		account.post(new WithdrawalTransaction(200));
		account.post(new PhoneBillPaymentTransaction("Vodafone", "5423345566", 96.50));
		BigDecimal outPut = account.getBalance();
		assertTrue(outPut.doubleValue() == 703.50);
	}

}
