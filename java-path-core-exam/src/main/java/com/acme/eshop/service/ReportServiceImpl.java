package com.acme.eshop.service;

import com.acme.eshop.EshopDemo;
import com.acme.eshop.domain.Customer;
import com.acme.eshop.domain.Product;

public class ReportServiceImpl extends AbstractServiceImpl implements ReportService {
	@Override
	public void totalNumberAndCostOfPurchases(Customer customer) {
		if (customer != null) {
			logger.info("Total number and cost of purchases for Customer[{}]", customer.getEmail());
			dataService.totalNumberAndCostOfPurchases(customer);
		}
	}

	@Override
	public void totalNumberAndCostOfPurchases(Product product) {
		if (product != null) {
			logger.info("Total number and cost of purchases for Product[{}]", product.getSerial());
			dataService.totalNumberAndCostOfPurchases(product);
		}
	}

	@Override
	public void totalNumberAndCostOfPurchasesPerCustomer() {
		logger.info("Total number and cost of purchases per customer");
		dataService.logData(EshopDemo.sqlCommands.getProperty("reports.003"));
	}

	@Override
	public void totalNumberAndCostOfPurchasesPerCustomerCategory() {
		logger.info("Total number and cost of purchases per customer category");
		dataService.logData(EshopDemo.sqlCommands.getProperty("reports.004"));
	}

	@Override
	public void totalNumberAndCostOfPurchasesPerPaymentMethod() {
		logger.info("Total number and cost of purchases per payment method");
		dataService.logData(EshopDemo.sqlCommands.getProperty("reports.005"));
	}

	@Override
	public void averageOrderCost() {
		logger.info("Average order cost");
		dataService.logData(EshopDemo.sqlCommands.getProperty("reports.006"));
	}

	@Override
	public void averageOrderCostPerCustomer() {
		logger.info("Average order cost per customer");
		dataService.logData(EshopDemo.sqlCommands.getProperty("reports.007"));
	}

	@Override
	public void getCustomersPurchasedMostExpensiveProduct() {
		logger.info("Customers who purchased most expensive product and how many times");
		dataService.logData(EshopDemo.sqlCommands.getProperty("reports.008"));
	}
}
