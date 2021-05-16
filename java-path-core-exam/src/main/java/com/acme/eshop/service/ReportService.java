package com.acme.eshop.service;

import com.acme.eshop.domain.Customer;
import com.acme.eshop.domain.Product;

public interface ReportService {
	void totalNumberAndCostOfPurchases(Customer customer);

	void totalNumberAndCostOfPurchases(Product product);

	void totalNumberAndCostOfPurchasesPerCustomer();

	void totalNumberAndCostOfPurchasesPerCustomerCategory();

	void totalNumberAndCostOfPurchasesPerPaymentMethod();

	void averageOrderCost();

	void averageOrderCostPerCustomer();

	void getCustomersPurchasedMostExpensiveProduct();

}
