package com.bank.api.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * An BankCustomer of a {@link Account}.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 * The Class TransactionBuilder.
 */
@Builder
@Document(collection = "transactions")
public class Transaction {

	/**
	 * The ID of the transaction. This is an oid determined by MongoDB. e.g.
	 * "5ff1eferg194b4f39b6e52a8314f".
	 */
	@Id
	private String id;

	/** The from account id. */
	private String fromAccountId;

	/** The to account id. */
	private String toAccountId;

	/** The transaction amount. */
	private double transactionAmount;

	/** The account id. */
	private String accountId;

	/** The transaction type. */
	private TransactionType transactionType;

	/**
	 * The date and time the Transaction was created.
	 */
	private LocalDateTime created;

}
