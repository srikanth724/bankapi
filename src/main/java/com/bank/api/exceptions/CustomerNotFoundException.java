package com.bank.api.exceptions;

import lombok.Getter;

/**
 * The Class CustomerNotFoundException.
 */
public class CustomerNotFoundException extends BankCustomerApplication {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7070145122150620295L;

	/** The Constant ERROR_MESSAGE. */
	private static final String ERROR_MESSAGE = "An Customer with ID '%s' does not exist";

	/** The customer id. */
	@Getter
	private String customerId;

	/**
	 * Instantiates a new customer not found exception.
	 *
	 * @param customerId the customer id
	 */
	public CustomerNotFoundException(final String customerId) {
		super(String.format(ERROR_MESSAGE, customerId));
		this.customerId = customerId;
	}

	/**
	 * Instantiates a new customer not found exception.
	 *
	 * @param customerId the customer id
	 * @param cause      the cause
	 */
	public CustomerNotFoundException(final String customerId, final Throwable cause) {
		super(String.format(ERROR_MESSAGE, customerId), cause);
		this.customerId = customerId;
	}
}
