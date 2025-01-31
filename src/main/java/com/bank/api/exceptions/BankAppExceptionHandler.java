package com.bank.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The Class BankAppExceptionHandler.
 */
@RestControllerAdvice
public class BankAppExceptionHandler {

	/**
	 * Handle resource not found exception.
	 *
	 * @param ex the ex
	 * @return the error response
	 */
	@ExceptionHandler(CustomerExistsException.class)
	public ErrorResponse handleResourceNotFoundException(CustomerExistsException ex) {
		return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
	}

	/**
	 * Handle resource not found exception.
	 *
	 * @param ex the ex
	 * @return the error response
	 */
	@ExceptionHandler(CustomerNotFoundException.class)
	public ErrorResponse handleResourceNotFoundException(CustomerNotFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	/**
	 * Handle resource not found exception.
	 *
	 * @param ex the ex
	 * @return the error response
	 */
	@ExceptionHandler(AccountNotFoundException.class)
	public ErrorResponse handleResourceNotFoundException(AccountNotFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}
}
