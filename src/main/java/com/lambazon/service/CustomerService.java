package com.lambazon.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lambazon.Application;
import com.lambazon.domain.Customer;
import com.lambazon.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Inject
	private CustomerRepository repository;
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	public List<Customer> customers() {
		List<Customer> target = new ArrayList<>();
		repository.findAll().iterator().forEachRemaining(target::add);
		log.info("OC-returning " + target.size() + " Customers from datastore");
		return target;
	}

	public Customer customer(Long id) {
		return repository.findById(id).get();
	}
	
	public Customer create(Customer customer) {
		return repository.save(customer);
	}
	
	public void update(Customer customer) {
		repository.save(customer);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

	public void deleteAll() {
		repository.deleteAll();
	}
}
