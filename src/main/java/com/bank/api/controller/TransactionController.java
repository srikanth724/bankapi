package com.bank.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.message.TransactionMessage;
import com.bank.api.model.Transaction;
import com.bank.api.service.TransactionService;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * The Class TransactionController.
 */
@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transaction", description = "Transactions API to create and fetch")
public class TransactionController {

	/** The transaction service. */
	@Autowired
	private TransactionService transactionService;

	/**
	 * Fetch transactions.
	 *
	 * @return the list
	 */
	@GetMapping("/all")
	public List<Transaction> fetchTransactions() {
		return transactionService.fetchAllTransactions();
	}

	/**
	 * Fetch transactions by account.
	 *
	 * @param accountId the account id
	 * @return the list
	 */
	@GetMapping("/account/{accountId}")
	public List<Transaction> fetchTransactionsByAccount(@PathVariable final String accountId) {
		return transactionService.findTransactionsByAccount(accountId);
	}

	/**
	 * Transfer balance between accounts.
	 *
	 * @param transactionMessage the transaction message
	 * @return the list
	 */
	@PostMapping("/transfer")
	public List<Transaction> transferBalanceBetweenAccounts(@RequestBody TransactionMessage transactionMessage) {
		return transactionService.transferBetweenAccounts(transactionMessage);
	}
}
