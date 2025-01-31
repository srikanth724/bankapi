package com.bank.api.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.Hidden;
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
 * The Class CustomerBuilder.
 */
@Builder
@Document(collection = "customers")
public class Customer {

	/**
	 * The ID of the customer. This is an oid determined by MongoDB. e.g.
	 * "5ff1eferg194b4f39b6e52a8314f".
	 */
	@Hidden
	@Id
	private String id;

	/**
	 * The customer's given name. e.g. "Srikanth".
	 */
	private String givenName;

	/**
	 * The customer's family name. e.g. "Nimmagadda".
	 */
	private String familyName;

	/**
	 * The customer's Email Address.
	 */
	private String emailAddress;

	/**
	 * The date and time the Customer was created.
	 */
	@Hidden
	private LocalDateTime created;

	/**
	 * The time and date the Customer was last update.
	 */
	@Hidden
	private LocalDateTime lastUpdated;

}
