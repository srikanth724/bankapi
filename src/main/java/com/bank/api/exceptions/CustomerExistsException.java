package com.bank.api.exceptions;

import lombok.Getter;

/**
 * The Class CustomerExistsException.
 */
public class CustomerExistsException extends BankCustomerApplication {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3346333399340164752L;

	/** The Constant ERROR_MESSAGE. */
	private static final String ERROR_MESSAGE = "An Customer with Email Address '%s' already exist";

	/** The email address. */
	@Getter
	private String emailAddress;

	/**
	 * Instantiates a new customer exists exception.
	 *
	 * @param emailAddress the email address
	 */
	public CustomerExistsException(final String emailAddress) {
		super(String.format(ERROR_MESSAGE, emailAddress));
		this.emailAddress = emailAddress;
	}

	/**
	 * Instantiates a new customer exists exception.
	 *
	 * @param emailAddress the email address
	 * @param cause        the cause
	 */
	public CustomerExistsException(final String emailAddress, final Throwable cause) {
		super(String.format(ERROR_MESSAGE, emailAddress), cause);
		this.emailAddress = emailAddress;
	}
}
