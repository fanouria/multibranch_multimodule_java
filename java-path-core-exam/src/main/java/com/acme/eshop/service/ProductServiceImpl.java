package com.acme.eshop.service;

import com.acme.eshop.domain.Product;

import java.util.List;

public class ProductServiceImpl extends AbstractServiceImpl implements ProductService {
	@Override
	public List<Product> getProducts() {
		return dataService.getProducts();
	}

	@Override
	public Product getProduct(String serial) {
		return dataService.getProduct(serial);
	}
}
