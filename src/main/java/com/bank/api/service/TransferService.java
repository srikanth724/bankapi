package com.bank.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.api.config.RabbitMQConfig;
import com.bank.api.exceptions.TransferNotFoundException;
import com.bank.api.message.TransactionMessage;
import com.bank.api.model.Transaction;
import com.bank.api.model.Transfer;
import com.bank.api.model.TransferStatus;
import com.bank.api.repository.TransferRepository;

/**
 * The Class TransferService.
 */
@Service
public class TransferService {

	/** The rabbit template. */
	@Autowired
	private RabbitTemplate rabbitTemplate;

	/** The transfer repository. */
	@Autowired
	private TransferRepository transferRepository;

	/** The transaction service. */
	@Autowired
	private TransactionService transactionService;

	/**
	 * Fetch transfer id.
	 *
	 * @param transferId the transfer id
	 * @return the transfer
	 */
	public Transfer fetchTransferId(String transferId) {
		return transferRepository.findById(transferId).orElseGet(() -> {
			throw new TransferNotFoundException(transferId);
		});
	}

	/**
	 * Send message.
	 *
	 * @param transactionMessage the transaction message
	 * @return the transfer
	 */
	public Transfer sendMessage(TransactionMessage transactionMessage) {
		// Create a Transfer Object
		Transfer transfer = Transfer.builder().fromAccountId(transactionMessage.getFromAccountId())
				.toAccountId(transactionMessage.getToAccountId())
				.transactionAmount(transactionMessage.getTransactionAmount()).transferStatus(TransferStatus.OPEN)
				.created(LocalDateTime.now()).build();
		transferRepository.save(transfer);
		transactionMessage.setTransferId(transfer.getId());

		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, transactionMessage);

		return transfer;
	}

	/**
	 * Receive message.
	 *
	 * @param transactionMessage the transaction message
	 */
	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
	public void receiveMessage(TransactionMessage transactionMessage) {

		System.out.println("Received message: " + transactionMessage);
		// TODO: Handle exception to make an update to transfer object
		Transfer transferExists = fetchTransferId(transactionMessage.getTransferId());
		// Just make the thread wait to test asynchronous events
		transferExists.setTransferStatus(TransferStatus.PROCESSING);
		transferExists.setCreated(LocalDateTime.now());
		transferRepository.save(transferExists);

		// Keeping the thread sleep to test scenarios
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<Transaction> transactions = transactionService.transferBetweenAccounts(transactionMessage);
		List<String> transactionIds = transactions.stream().map(Transaction::getId).collect(Collectors.toList());

		transferExists.setCreated(LocalDateTime.now());
		transferExists.setTransferStatus(TransferStatus.COMPLETED);
		transferExists.setTransactionIds(transactionIds);

		transferRepository.save(transferExists);
	}
}
