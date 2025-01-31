package com.bank.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class ErrorResponse.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	/** The status code. */
	private int statusCode;

	/** The message. */
	private String message;

	/**
	 * Instantiates a new error response.
	 *
	 * @param message the message
	 */
	public ErrorResponse(String message) {
		super();
		this.message = message;
	}

}
