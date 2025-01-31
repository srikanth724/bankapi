package com.bank.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.api.exceptions.CustomerExistsException;
import com.bank.api.exceptions.CustomerNotFoundException;
import com.bank.api.model.Customer;
import com.bank.api.repository.CustomerRepository;

/**
 * The Class CustomerService.
 */
@Service
public class CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	/** The customer repository. */
	@Autowired
	private CustomerRepository customerRepository;

	/** The account service. */
	@Autowired
	private AccountService accountService;

	/** The Constant INITIAL_AMOUNT. */
	public static final double INITIAL_AMOUNT = 1000.0;

	/**
	 * Fetch all customers.
	 *
	 * @return the list
	 */
	public List<Customer> fetchAllCustomers() {
		logger.info("INFO: Fetching all customers");
		return customerRepository.findAll();
	}

	/**
	 * Fetch customer by id.
	 *
	 * @param customerId the customer id
	 * @return the customer
	 */
	public Customer fetchCustomerById(String customerId) {
		return customerRepository.findById(customerId).orElseGet(() -> {
			throw new CustomerNotFoundException(customerId);
		});
	}

	/**
	 * Creates the customer with initial balance.
	 *
	 * @param customerRequest the customer request
	 * @return the customer
	 */
	public Customer createCustomerWithInitialBalance(Customer customerRequest) {

		logger.info("Processing INFO: createCustomerWithInitialBalance" + customerRequest.toString());

		// Validate if the customer exists based on emailAddress
		Customer existingCustomer = customerRepository.findByEmailAddress(customerRequest.getEmailAddress());

		if (existingCustomer != null) {
			throw new CustomerExistsException(existingCustomer.getEmailAddress());
		}

		// Create a new Customer
		final LocalDateTime now = LocalDateTime.now();
		Customer customer = Customer.builder().givenName(customerRequest.getGivenName())
				.familyName(customerRequest.getFamilyName()).emailAddress(customerRequest.getEmailAddress())
				.created(now).lastUpdated(now).build();

		customerRepository.save(customer);

		accountService.createAccountWithInitialBalance(customer.getId());

		logger.info("Completed Processing INFO: createCustomerWithInitialBalance" + customer.toString());

		return customer;
	}

}
