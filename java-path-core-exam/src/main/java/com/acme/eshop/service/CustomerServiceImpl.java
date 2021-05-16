package com.acme.eshop.service;

import com.acme.eshop.domain.Customer;

import java.util.List;

public class CustomerServiceImpl extends AbstractServiceImpl implements CustomerService {
	@Override
	public boolean register(Customer customer) {
		return dataService.save(customer);
	}

	@Override
	public List<Customer> getCustomers() {
		return dataService.getCustomers();
	}

	@Override
	public Customer getCustomer(Long id) {
		return dataService.getCustomer(id);
	}

	@Override
	public Customer getCustomer(String email) {
		return dataService.getCustomer(email);
	}
}
