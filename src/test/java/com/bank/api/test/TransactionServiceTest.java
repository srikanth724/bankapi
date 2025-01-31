package com.bank.api.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bank.api.message.TransactionMessage;
import com.bank.api.model.Account;
import com.bank.api.model.Transaction;
import com.bank.api.model.TransactionType;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.TransactionRepository;
import com.bank.api.service.AccountService;
import com.bank.api.service.TransactionService;

/**
 * The Class TransactionServiceTest.
 */
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

	/** The transaction repository. */
	@Mock
	private TransactionRepository transactionRepository;

	/** The account repository. */
	@Mock
	private AccountRepository accountRepository;

	/** The account service. */
	@Mock
	private AccountService accountService;

	/** The transaction service. */
	@InjectMocks
	private TransactionService transactionService;

	/** The from account. */
	private Account fromAccount;

	/** The to account. */
	private Account toAccount;

	/** The transaction. */
	private Transaction transaction;

	/** The transaction message. */
	private TransactionMessage transactionMessage;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		fromAccount = new Account();
		fromAccount.setId("679bf5861a1f2c21ce46fc67");
		fromAccount.setAccountBalance(5000.0);

		toAccount = new Account();
		toAccount.setId("679c12aa698c2f1a09e825d1");
		toAccount.setAccountBalance(2000.0);

		transaction = Transaction.builder().fromAccountId("fromAcc").toAccountId("toAcc").transactionAmount(1000.0)
				.transactionType(TransactionType.TRANSFER_FROM).created(LocalDateTime.now()).build();

		transactionMessage = TransactionMessage.builder().fromAccountId("fromAcc").toAccountId("toAcc")
				.transactionAmount(1000.0).build();

	}

	/**
	 * Test fetch all transactions.
	 */
	@Test
    void testFetchAllTransactions() {
        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction));
        List<Transaction> transactions = transactionService.fetchAllTransactions();
        assertFalse(transactions.isEmpty());
        assertEquals(1, transactions.size());
        verify(transactionRepository, times(1)).findAll();
    }

	/**
	 * Test find transactions by account.
	 */
	@Test
    void testFindTransactionsByAccount() {
        when(transactionRepository.findTransactionByAccountId("fromAcc")).thenReturn(Arrays.asList(transaction));
        List<Transaction> transactions = transactionService.findTransactionsByAccount("fromAcc");
        assertFalse(transactions.isEmpty());
        assertEquals(1, transactions.size());
        verify(transactionRepository, times(1)).findTransactionByAccountId("fromAcc");
    }

	/**
	 * Test transfer between accounts success.
	 */
	@Test
    void testTransferBetweenAccounts_Success() {
        when(accountService.fetchAccountById("fromAcc")).thenReturn(fromAccount);
        when(accountService.fetchAccountById("toAcc")).thenReturn(toAccount);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        List<Transaction> result = transactionService.transferBetweenAccounts(transactionMessage);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(accountRepository, times(2)).save(any(Account.class));
        verify(transactionRepository, times(2)).save(any(Transaction.class));
        verify(accountService, times(1)).fetchAccountById("fromAcc");
        verify(accountService, times(1)).fetchAccountById("toAcc");
    }

	/**
	 * Test transfer between accounts insufficient funds.
	 */
	@Test
	void testTransferBetweenAccounts_InsufficientFunds() {
		fromAccount.setAccountBalance(500.00);
		when(accountService.fetchAccountById("fromAcc")).thenReturn(fromAccount);
		when(accountService.fetchAccountById("toAcc")).thenReturn(toAccount);

		List<Transaction> result = transactionService.transferBetweenAccounts(transactionMessage);

		assertNull(result);
		verify(accountService, times(1)).fetchAccountById("fromAcc");
		verify(accountService, times(1)).fetchAccountById("toAcc");
	}

	/**
	 * Test transfer between accounts invalid accounts.
	 */
	@Test
    void testTransferBetweenAccounts_InvalidAccounts() {
        when(accountService.fetchAccountById("fromAcc")).thenReturn(null);
        when(accountService.fetchAccountById("toAcc")).thenReturn(null);

        List<Transaction> result = transactionService.transferBetweenAccounts(transactionMessage);

        assertNull(result);
        verify(accountService, times(1)).fetchAccountById("fromAcc");
        verify(accountService, times(1)).fetchAccountById("toAcc");
    }
}
