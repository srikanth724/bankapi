package com.bank.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.model.Account;
import com.bank.api.service.AccountService;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * The Class AccountController.
 */
@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Account", description = "Account API to fetch or create accounts")
public class AccountController {

	/** The account service. */
	@Autowired
	private AccountService accountService;

	/**
	 * Fetch account by id.
	 *
	 * @param accountId the account id
	 * @return the account
	 */
	@GetMapping("/{accountId}")
	public Account fetchAccountById(@PathVariable final String accountId) {
		return accountService.fetchAccountById(accountId);
	}

	/**
	 * Fetch accounts.
	 *
	 * @return the list
	 */
	@GetMapping("/all")
	public List<Account> fetchAccounts() {
		return accountService.fetchAllAccounts();
	}

	/**
	 * Fetch accounts by customer.
	 *
	 * @param customerId the customer id
	 * @return the list
	 */
	@GetMapping("/all/{customerId}")
	public List<Account> fetchAccountsByCustomer(@PathVariable final String customerId) {
		return accountService.fetchAllAccountsByCustomerId(customerId);
	}

	/**
	 * Creates the additonal accounts for customer.
	 *
	 * @param customerId the customer id
	 * @return the account
	 */
	@PostMapping("/create/{customerId}")
	public Account createAdditonalAccountsForCustomer(@PathVariable final String customerId) {
		return accountService.createAccountWithInitialBalance(customerId);
	}

}
