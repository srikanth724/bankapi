package com.bank.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bank.api.model.Transfer;

import io.swagger.v3.oas.annotations.Hidden;

/**
 * The Interface TransferRepository.
 */
@Hidden
public interface TransferRepository extends MongoRepository<Transfer, String> {

}
