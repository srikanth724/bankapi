package com.bank.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.model.Customer;
import com.bank.api.service.CustomerService;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * The Class CustomerController.
 */
@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer", description = "Customer API to create customers")
public class CustomerController {

	/** The customer service. */
	@Autowired
	private CustomerService customerService;

	/**
	 * Fetch customer by id.
	 *
	 * @param customerId the customer id
	 * @return the customer
	 */
	@GetMapping("/{customerId}")
	public Customer fetchCustomerById(@PathVariable final String customerId) {
		return customerService.fetchCustomerById(customerId);
	}

	/**
	 * Fetch customers.
	 *
	 * @return the list
	 */
	@GetMapping("/all")
	public List<Customer> fetchCustomers() {
		return customerService.fetchAllCustomers();
	}

	/**
	 * Creates the customer with initial balance.
	 *
	 * @param createUpdateCustomerRequestBody the create update customer request
	 *                                        body
	 * @return the customer
	 */
	@PostMapping("/create")
	public Customer createCustomerWithInitialBalance(@RequestBody Customer createUpdateCustomerRequestBody) {
		return customerService.createCustomerWithInitialBalance(createUpdateCustomerRequestBody);
	}

}
