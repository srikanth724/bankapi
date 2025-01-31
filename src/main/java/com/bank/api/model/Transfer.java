package com.bank.api.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class Transfer.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 * The Class TransferBuilder.
 */
@Builder
@Document(collection = "transfers")
public class Transfer {

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

	/** The transaction ids. */
	private List<String> transactionIds;

	/** The transfer status. */
	private TransferStatus transferStatus;

	/**
	 * The date and time the Transaction was created.
	 */
	private LocalDateTime created;

}
