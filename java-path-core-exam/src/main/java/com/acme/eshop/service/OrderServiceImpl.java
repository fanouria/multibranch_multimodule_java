package com.acme.eshop.service;

import com.acme.eshop.domain.Customer;
import com.acme.eshop.domain.Order;
import com.acme.eshop.domain.OrderItem;
import com.acme.eshop.domain.PaymentMethod;
import com.acme.eshop.domain.Product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl extends AbstractServiceImpl implements OrderService {
	@Override
	public Order initiateOrder(Customer customer) {
		return Order.builder(customer).build();
	}

	@Override
	public List<Order> getOrders() {
		return dataService.getOrders();
	}

	@Override
	public Order getOrder(Long id) {
		return dataService.getOrder(id);
	}

	@Override
	public void addItem(Order order, Product product, int quantity) {
		if (checkNullability(order, product)) return;

		boolean increasedQuantity = false;

		// If product is already contained in the order, don't add it again, just increase the quantity accordingly
		for (OrderItem oi : order.getOrderItems()) {
			if (oi.getSerial().equals(product.getSerial())) {
				oi.setQuantity(oi.getQuantity() + quantity);
				increasedQuantity = true;
				break;
			}
		}

		if (!increasedQuantity) {
			order.add(OrderItem.builder(product.getSerial(), quantity, product.getPrice()).build());
		}

		logger.debug("Product[{}] added to Order[{}]", product, order);
	}

	private boolean checkNullability(Order order, Product product) {
		if (order == null) {
			logger.warn("Order is null.");
			return true;
		}
		if (product == null) {
			logger.warn("Product is null.");
			return true;
		}
		return false;
	}

	@Override
	public void updateItem(Order order, Product product, int quantity) {
		if (checkNullability(order, product)) return;

		order.getOrderItems().removeIf(oi -> oi.getSerial().equals(product.getSerial()));
		order.add(OrderItem.builder(product.getSerial(), quantity, product.getPrice()).build());

		logger.debug("Product[{}] updated in Order[{}]", product, order);
	}

	@Override
	public void removeItem(Order order, Product product) {
		if (checkNullability(order, product)) return;

		order.getOrderItems().removeIf(oi -> oi.getSerial().equals(product.getSerial()));
		logger.debug("Product[{}] removed from Order[{}]", product, order);
	}

	@Override
	public void checkout(Order order, PaymentMethod paymentMethod) {
		if (!validate(order)) {
			logger.warn("Order should have customer, order items, and payment type defined before being able to be " +
								"checkout the order.");
			return;
		}

		// Set all order fields with proper values
		order.setPaymentMethod(paymentMethod);
		order.setSubmitDate(new Date());
		order.setCost(giveDiscounts(order));

		dataService.save(order);
	}

	private boolean validate(Order order) {
		return order != null && !order.getOrderItems().isEmpty() && order.getCustomer() != null;
	}

	private BigDecimal giveDiscounts(Order order) {
		float totalDiscount =
				order.getCustomer().getCustomerCategory().getDiscount() + order.getPaymentMethod().getDiscount();

		// Calculate original order cost
		//@formatter:off
		BigDecimal originalCost = order.getOrderItems().stream()
				.map(oi -> oi.getPrice().multiply(BigDecimal.valueOf(oi.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		//@formatter:on

		// Apply discount
		BigDecimal finalCost = originalCost.multiply(BigDecimal.valueOf(1f - totalDiscount));

		logger.debug("Order[{}], originalCost: {}, totalDiscount: {}, finalCost: {}.", order.getId(), originalCost,
					 totalDiscount, finalCost);

		return finalCost;
	}
}
