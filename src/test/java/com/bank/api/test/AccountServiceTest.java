package com.bank.api.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bank.api.exceptions.AccountNotFoundException;
import com.bank.api.exceptions.CustomerNotFoundException;
import com.bank.api.model.Account;
import com.bank.api.model.Customer;
import com.bank.api.model.Transaction;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.CustomerRepository;
import com.bank.api.repository.TransactionRepository;
import com.bank.api.service.AccountService;

/**
 * The Class AccountServiceTest.
 */
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	/** The account repository. */
	@Mock
	private AccountRepository accountRepository;

	/** The customer repository. */
	@Mock
	private CustomerRepository customerRepository;

	/** The transaction repository. */
	@Mock
	private TransactionRepository transactionRepository;

	/** The account service. */
	@InjectMocks
	private AccountService accountService;

	/** The account. */
	private Account account;

	/** The customer. */
	private Customer customer;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		customer = new Customer();
		customer.setId("cust123");

		account = Account.builder().id("acc123").accountBalance(AccountService.INITIAL_AMOUNT).customer(customer)
				.dateDeposited(LocalDateTime.now()).lastUpdated(LocalDateTime.now()).build();
	}

	/**
	 * Test fetch all accounts.
	 */
	@Test
    void testFetchAllAccounts() {
        when(accountRepository.findAll()).thenReturn(Arrays.asList(account));
        List<Account> accounts = accountService.fetchAllAccounts();
        assertFalse(accounts.isEmpty());
        assertEquals(1, accounts.size());
        Mockito.verify(accountRepository, times(1)).findAll();
    }

	/**
	 * Test fetch all accounts by customer id.
	 */
	@Test
    void testFetchAllAccountsByCustomerId() {
        when(accountRepository.findAccountByCustomer_Id("cust123")).thenReturn(Arrays.asList(account));
        List<Account> accounts = accountService.fetchAllAccountsByCustomerId("cust123");
        assertFalse(accounts.isEmpty());
        assertEquals(1, accounts.size());
        Mockito.verify(accountRepository, times(1)).findAccountByCustomer_Id("cust123");
    }

	/**
	 * Test fetch account by id success.
	 */
	@Test
    void testFetchAccountById_Success() {
        when(accountRepository.findById("acc123")).thenReturn(Optional.of(account));
        Account result = accountService.fetchAccountById("acc123");
        assertNotNull(result);
        assertEquals("acc123", result.getId());
        Mockito.verify(accountRepository, times(1)).findById("acc123");
    }

	/**
	 * Test fetch account by id throws exception.
	 */
	@Test
    void testFetchAccountById_ThrowsException() {
        when(accountRepository.findById("invalid"))
                .thenReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class, () -> accountService.fetchAccountById("invalid"));
        Mockito.verify(accountRepository, times(1)).findById("invalid");
    }

	/**
	 * Test create account with initial balance success.
	 */
	@Test
    void testCreateAccountWithInitialBalance_Success() {
        when(customerRepository.findById("cust123")).thenReturn(Optional.of(customer));
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(new Transaction());

        Account createdAccount = accountService.createAccountWithInitialBalance("cust123");

        assertNotNull(createdAccount);
        assertEquals(AccountService.INITIAL_AMOUNT, createdAccount.getAccountBalance());
        verify(accountRepository, times(1)).save(any(Account.class));
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        Mockito.verify(customerRepository, times(1)).findById("cust123");
    }

	/**
	 * Test create account with initial balance throws customer not found exception.
	 */
	@Test
    void testCreateAccountWithInitialBalance_ThrowsCustomerNotFoundException() {
        when(customerRepository.findById("invalid"))
                .thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> accountService.createAccountWithInitialBalance("invalid"));
        Mockito.verify(customerRepository, times(1)).findById("invalid");
    }
}