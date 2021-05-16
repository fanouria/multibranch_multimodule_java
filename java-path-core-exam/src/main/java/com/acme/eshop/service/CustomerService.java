package com.acme.eshop.service;

import com.acme.eshop.domain.Customer;

import java.util.List;

public interface CustomerService {
	boolean register(Customer customer);

	List<Customer> getCustomers();

	Customer getCustomer(Long id);

	Customer getCustomer(String email);
}
