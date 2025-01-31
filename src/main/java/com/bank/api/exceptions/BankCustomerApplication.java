package com.bank.api.exceptions;

/**
 * Base exception for the whole CustomerController application.
 */
public class BankCustomerApplication extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -892086076183142925L;

	/**
	 * Instantiates a new bank customer application.
	 */
	public BankCustomerApplication() {
	}

	/**
	 * Instantiates a new bank customer application.
	 *
	 * @param message the message
	 */
	public BankCustomerApplication(String message) {
		super(message);
	}

	/**
	 * Instantiates a new bank customer application.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public BankCustomerApplication(String message, Throwable cause) {
		super(message, cause);
	}

}
