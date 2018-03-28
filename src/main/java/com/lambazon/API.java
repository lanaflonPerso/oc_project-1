package com.lambazon;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;

import com.lambazon.domain.Customer;
import com.lambazon.domain.Order;
import com.lambazon.domain.Product;
import com.lambazon.repository.CustomerRepository;
import com.lambazon.repository.ProductRepository;

@Configuration
public class API {
	
	@Inject
	private CustomerRepository customerRepository;
	
	@Inject
	private ProductRepository productRepository;

	/**
	 * 
	 * Create a Customer with given argument
	 * 
	 * @param name
	 * @return Customer
	 */
	public Customer createCustomer(String name) {
		Customer customer = new Customer(name);
		return customerRepository.create(customer);
	}

	/**
	 * 
	 * Create a Product with given arguments
	 * 
	 * @param id
	 * @param quantity
	 * @param price
	 * @param name
	 * @param description
	 * @return Product
	 */
	public Product createProduct(int quantity, double price, String name, String description) {
		Product  product = new Product(quantity, price, name, description);
		return productRepository.create(product);
	}

	/**
	 * 
	 * Create an Order given argument  <TODO>Order persistence is pending</TODO>
	 * 
	 * @param customer
	 * @return Order
	 */

	public Order createOrder(Customer customer) {
		return new Order(customer);
	}
	
	
	// Initialize database
	public void deleteAll() {
		customerRepository.deleteAll();
		productRepository.deleteAll();
		
	}
}
