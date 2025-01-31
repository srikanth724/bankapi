package com.bank.api.exceptions;

import lombok.Getter;

/**
 * The Class TransferNotFoundException.
 */
public class TransferNotFoundException extends BankCustomerApplication {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant ERROR_MESSAGE. */
	private static final String ERROR_MESSAGE = "An Transfer with ID '%s' does not exist";

	/** The transfer id. */
	@Getter
	private String transferId;

	/**
	 * Instantiates a new transfer not found exception.
	 *
	 * @param transferId the transfer id
	 */
	public TransferNotFoundException(final String transferId) {
		super(String.format(ERROR_MESSAGE, transferId));
		this.transferId = transferId;
	}

	/**
	 * Instantiates a new transfer not found exception.
	 *
	 * @param transferId the transfer id
	 * @param cause      the cause
	 */
	public TransferNotFoundException(final String transferId, final Throwable cause) {
		super(String.format(ERROR_MESSAGE, transferId), cause);
		this.transferId = transferId;
	}
}
