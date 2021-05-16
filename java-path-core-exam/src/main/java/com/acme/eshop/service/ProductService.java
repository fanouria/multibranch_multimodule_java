package com.acme.eshop.service;

import com.acme.eshop.domain.Product;

import java.util.List;

public interface ProductService {
	List<Product> getProducts();

	Product getProduct(String serial);
}
