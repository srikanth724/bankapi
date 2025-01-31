package com.bank.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.api.message.TransactionMessage;
import com.bank.api.model.Account;
import com.bank.api.model.Transaction;
import com.bank.api.model.TransactionType;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.TransactionRepository;

/**
 * The Class TransactionService.
 */
@Service
public class TransactionService {

	/** The transaction repository. */
	@Autowired
	private TransactionRepository transactionRepository;

	/** The account repository. */
	@Autowired
	private AccountRepository accountRepository;

	/** The account service. */
	@Autowired
	private AccountService accountService;

	/**
	 * Fetch all transactions.
	 *
	 * @return the list
	 */
	public List<Transaction> fetchAllTransactions() {
		return transactionRepository.findAll();
	}

	/**
	 * Find transactions by account.
	 *
	 * @param accountId the account id
	 * @return the list
	 */
	public List<Transaction> findTransactionsByAccount(String accountId) {
		return transactionRepository.findTransactionByAccountId(accountId);
	}

	/**
	 * Transfer between accounts.
	 *
	 * @param makeTransaction the make transaction
	 * @return the list
	 */
	public List<Transaction> transferBetweenAccounts(TransactionMessage makeTransaction) {

		Account fromAccountExists = accountService.fetchAccountById(makeTransaction.getFromAccountId());
		Account toAccountExists = accountService.fetchAccountById(makeTransaction.getToAccountId());

		if (fromAccountExists == null || toAccountExists == null) {
			// Write code to throw an exception saying Invalid transaction one/both accounts
			// does not exists
			return null;
		}

		// Check whether the customer has sufficient balance to transfer
		if (fromAccountExists.getAccountBalance() == 0
				|| fromAccountExists.getAccountBalance() < makeTransaction.getTransactionAmount()) {
			// Write code to throw an exception saying Insufficient funds
			return null;
		}

		final LocalDateTime now = LocalDateTime.now();
		// Make changes to sender account and create a transaction for sender account
		double newFromAccountBalance = fromAccountExists.getAccountBalance() - makeTransaction.getTransactionAmount();
		// Create and update the initial amount
		fromAccountExists.setAccountBalance(newFromAccountBalance);
		fromAccountExists.setCustomer(fromAccountExists.getCustomer());
		fromAccountExists.setLastUpdated(now);

		accountRepository.save(fromAccountExists);

		// Create Transactions for FromAccount
		// Add transaction to transaction table
		final Transaction fromAccountTransaction = Transaction.builder().accountId(fromAccountExists.getId())
				.transactionAmount(makeTransaction.getTransactionAmount()).toAccountId(toAccountExists.getId())
				.transactionType(TransactionType.TRANSFER_FROM).created(now).build();

		transactionRepository.save(fromAccountTransaction);

		// Create and save transfer to account
		// Update the balance in transfer to account
		double newToAccountBalance = toAccountExists.getAccountBalance() + makeTransaction.getTransactionAmount();
		// Create and update the initial amount
		toAccountExists.setAccountBalance(newToAccountBalance);
		toAccountExists.setCustomer(toAccountExists.getCustomer());
		toAccountExists.setLastUpdated(now);

		accountRepository.save(toAccountExists);

		// Create Transactions for ToAccount
		// Add transaction to transaction table
		final Transaction toAccountTransaction = Transaction.builder().accountId(toAccountExists.getId())
				.transactionAmount(makeTransaction.getTransactionAmount()).fromAccountId(fromAccountExists.getId())
				.transactionType(TransactionType.TRANSFER_TO).created(now).build();

		transactionRepository.save(toAccountTransaction);

		List<Transaction> resultantArrayofTransactions = new ArrayList<Transaction>();
		resultantArrayofTransactions.add(fromAccountTransaction);
		resultantArrayofTransactions.add(toAccountTransaction);
		return resultantArrayofTransactions;
	}

}
