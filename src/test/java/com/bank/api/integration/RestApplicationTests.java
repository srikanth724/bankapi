package com.bank.api.integration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.api.Application;

/**
 * The Class RestApplicationTests.
 */
@SpringBootTest(classes = { Application.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestApplicationTests {

	/**
	 * Context loads.
	 */
	@Test
	void contextLoads() {
	}

}
