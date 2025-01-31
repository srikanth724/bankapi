package com.bank.api.exceptions;

import lombok.Getter;

/**
 * The Class TransactionNotFoundException.
 */
public class TransactionNotFoundException extends BankCustomerApplication {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3859218350865376712L;

	/** The Constant ERROR_MESSAGE. */
	private static final String ERROR_MESSAGE = "An Transaction with ID '%s' does not exist";

	/** The transaction id. */
	@Getter
	private String transactionId;

	/**
	 * Instantiates a new transaction not found exception.
	 *
	 * @param transactionId the transaction id
	 */
	public TransactionNotFoundException(final String transactionId) {
		super(String.format(ERROR_MESSAGE, transactionId));
		this.transactionId = transactionId;
	}

	/**
	 * Instantiates a new transaction not found exception.
	 *
	 * @param transactionId the transaction id
	 * @param cause         the cause
	 */
	public TransactionNotFoundException(final String transactionId, final Throwable cause) {
		super(String.format(ERROR_MESSAGE, transactionId), cause);
		this.transactionId = transactionId;
	}
}
