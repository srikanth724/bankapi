package com.bank.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bank.api.model.Transaction;

import io.swagger.v3.oas.annotations.Hidden;

/**
 * The Interface TransactionRepository.
 */
@Hidden
public interface TransactionRepository extends MongoRepository<Transaction, String> {

	/**
	 * Find transaction by account id.
	 *
	 * @param accountId the account id
	 * @return the list
	 */
	List<Transaction> findTransactionByAccountId(String accountId);

}
