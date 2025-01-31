package com.bank.api.message;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class TransactionMessage.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 * The Class TransactionMessageBuilder.
 */
@Builder
public class TransactionMessage implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3328533452285661684L;

	/** The transfer id. */
	private String transferId;

	/** The from account id. */
	private String fromAccountId;

	/** The to account id. */
	private String toAccountId;

	/** The transaction amount. */
	private double transactionAmount;

}
