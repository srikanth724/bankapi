package com.bank.api.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account owned by an {@link Customer}.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 * The Class AccountBuilder.
 */
@Builder
@Schema(hidden = true)
@Document(collection = "accounts")
public class Account {

	/**
	 * The ID of the BankAccount. This is an oid determined by MongoDB. e.g.
	 * "5ff1eferg194b4f39b6e52a8314f".
	 */
	@Id
	private String id;

	/**
	 * The accountBalance.
	 */
	private Double accountBalance;

	/**
	 * The date the account was created and deposited.
	 */
	private LocalDateTime dateDeposited;

	/**
	 * The ID of the author. This is the oid of the customer in the customer's Mongo
	 * collection. e.g. "5ff1eferg194b4f39b6e52a8314f".
	 */
	private Customer customer;

	/**
	 * The date and time the account was last updated.
	 */
	private LocalDateTime lastUpdated;

}
