package gr.codehub.core.showcase.testing.service;

import gr.codehub.core.showcase.testing.domain.Item;

import java.util.List;

public interface CartService {
	boolean addItem(Item item);

	boolean removeItem(Item item);

	List<Item> getCartItems();

	double getTotalPrice();

	boolean checkout();
}
