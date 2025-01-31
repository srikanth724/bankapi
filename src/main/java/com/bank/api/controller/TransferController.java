package com.bank.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.message.TransactionMessage;
import com.bank.api.model.Transfer;
import com.bank.api.service.TransferService;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * The Class TransferController.
 */
@RestController
@RequestMapping("/api/transfers")
@Tag(name = "Transfer", description = "API's to help transfer balance from one account into another")
public class TransferController {

	/** The transfer service. */
	@Autowired
	private TransferService transferService;

	/**
	 * Fetch account by id.
	 *
	 * @param transferId the transfer id
	 * @return the transfer
	 */
	@GetMapping("/{transferId}")
	public Transfer fetchAccountById(@PathVariable final String transferId) {
		return transferService.fetchTransferId(transferId);
	}

	/**
	 * Transfer balance between accounts event.
	 *
	 * @param transactionMessage the transaction message
	 * @return the transfer
	 */
	@PostMapping("/transferEvent")
	public Transfer transferBalanceBetweenAccountsEvent(@RequestBody TransactionMessage transactionMessage) {
		return transferService.sendMessage(transactionMessage);
	}

}
