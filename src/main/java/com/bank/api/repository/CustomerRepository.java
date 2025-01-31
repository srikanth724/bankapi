package com.bank.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bank.api.model.Customer;

import io.swagger.v3.oas.annotations.Hidden;

/**
 * The Interface CustomerRepository.
 */
@Hidden
public interface CustomerRepository extends MongoRepository<Customer, String> {

	/**
	 * Find by email address.
	 *
	 * @param emailAddress the email address
	 * @return the customer
	 */
	Customer findByEmailAddress(String emailAddress);
}
