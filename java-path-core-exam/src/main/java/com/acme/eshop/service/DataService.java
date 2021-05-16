package com.acme.eshop.service;

import com.acme.eshop.domain.Customer;
import com.acme.eshop.domain.Order;
import com.acme.eshop.domain.Product;

import java.util.List;

public interface DataService {
	List<Customer> getCustomers();

	Customer getCustomer(Long id);

	Customer getCustomer(String email);

	boolean save(Customer customer);

	List<Order> getOrders();

	Order getOrder(Long id);

	boolean save(Order order);

	List<Product> getProducts();

	Product getProduct(String serial);

	void totalNumberAndCostOfPurchases(Customer customer);

	void totalNumberAndCostOfPurchases(Product product);

	void logData(String command);
}
