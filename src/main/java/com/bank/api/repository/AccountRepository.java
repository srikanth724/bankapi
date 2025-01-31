package com.bank.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bank.api.model.Account;

import io.swagger.v3.oas.annotations.Hidden;

/**
 * The Interface AccountRepository.
 */
@Hidden
public interface AccountRepository extends MongoRepository<Account, String> {

	/**
	 * Find account by customer id.
	 *
	 * @param customerId the customer id
	 * @return the list
	 */
	List<Account> findAccountByCustomer_Id(String customerId);
}
