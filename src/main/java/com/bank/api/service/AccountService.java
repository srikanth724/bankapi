package com.bank.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.api.exceptions.AccountNotFoundException;
import com.bank.api.exceptions.CustomerNotFoundException;
import com.bank.api.model.Account;
import com.bank.api.model.Transaction;
import com.bank.api.model.TransactionType;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.CustomerRepository;
import com.bank.api.repository.TransactionRepository;

/**
 * The Class AccountService.
 */
@Service
public class AccountService {

	/** The account repository. */
	@Autowired
	private AccountRepository accountRepository;

	/** The customer repository. */
	@Autowired
	private CustomerRepository customerRepository;

	/** The transaction repository. */
	@Autowired
	private TransactionRepository transactionRepository;

	/** The Constant INITIAL_AMOUNT. */
	public static final double INITIAL_AMOUNT = 1000.0;

	/**
	 * Fetch all accounts.
	 *
	 * @return the list
	 */
	public List<Account> fetchAllAccounts() {
		return accountRepository.findAll();
	}

	/**
	 * Fetch all accounts by customer id.
	 *
	 * @param accountId the account id
	 * @return the list
	 */
	public List<Account> fetchAllAccountsByCustomerId(String accountId) {
		return accountRepository.findAccountByCustomer_Id(accountId);
	}

	/**
	 * Fetch account by id.
	 *
	 * @param accountId the account id
	 * @return the account
	 */
	public Account fetchAccountById(String accountId) {
		return accountRepository.findById(accountId).orElseGet(() -> {
			throw new AccountNotFoundException(accountId);
		});
	}

	/**
	 * Creates the account with initial balance.
	 *
	 * @param customerId the customer id
	 * @return the account
	 */
	public Account createAccountWithInitialBalance(String customerId) {
		return customerRepository.findById(customerId).map(existingCustomer -> {
			final LocalDateTime now = LocalDateTime.now();
			// Create and save the initial account
			Account account = Account.builder().accountBalance(INITIAL_AMOUNT).dateDeposited(now)
					.customer(existingCustomer).lastUpdated(now).build();
			accountRepository.save(account);

			// Add transaction to transaction table
			final Transaction transaction = Transaction.builder().accountId(account.getId())
					.transactionAmount(INITIAL_AMOUNT).transactionType(TransactionType.INITIAL_DEPOSIT)
					.created(now).build();

			transactionRepository.save(transaction);

			return account;
		}).orElseGet(() -> {
			throw new CustomerNotFoundException(customerId);
		});
	}

}
