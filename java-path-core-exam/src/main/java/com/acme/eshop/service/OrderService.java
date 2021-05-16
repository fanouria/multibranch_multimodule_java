package com.acme.eshop.service;

import com.acme.eshop.domain.Customer;
import com.acme.eshop.domain.Order;
import com.acme.eshop.domain.PaymentMethod;
import com.acme.eshop.domain.Product;

import java.util.List;

public interface OrderService {
	Order initiateOrder(Customer customer);

	List<Order> getOrders();

	Order getOrder(Long id);

	void addItem(Order Order, Product product, int quantity);

	void updateItem(Order order, Product product, int quantity);

	void removeItem(Order order, Product product);

	void checkout(Order order, PaymentMethod paymentMethod);
}
