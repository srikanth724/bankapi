package com.bank.api.exceptions;

import lombok.Getter;

/**
 * The Class AccountNotFoundException.
 */
public class AccountNotFoundException extends BankCustomerApplication {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant ERROR_MESSAGE. */
	private static final String ERROR_MESSAGE = "An Account with ID '%s' does not exist";

	/** The account id. */
	@Getter
	private String accountId;

	/**
	 * Instantiates a new account not found exception.
	 *
	 * @param accountId the account id
	 */
	public AccountNotFoundException(final String accountId) {
		super(String.format(ERROR_MESSAGE, accountId));
		this.accountId = accountId;
	}

	/**
	 * Instantiates a new account not found exception.
	 *
	 * @param accountId the account id
	 * @param cause     the cause
	 */
	public AccountNotFoundException(final String accountId, final Throwable cause) {
		super(String.format(ERROR_MESSAGE, accountId), cause);
		this.accountId = accountId;
	}
}
