package gr.codehub.core.showcase.testing.service;

import gr.codehub.core.showcase.testing.domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

	private CartServiceImpl cartService;

	@Mock
	private PaymentService mockedPaymentService;

	@BeforeEach
	void initialize() {
		cartService = new CartServiceImpl(mockedPaymentService);
		cartService.addItem(new Item("CPU", 300, 1));
		cartService.addItem(new Item("RAM", 120, 2));
	}

	@Test
	@DisplayName("Add correct item in cart")
	void addItem() {
		boolean result = cartService.addItem(new Item("SSD", 80, 2));
		assertTrue(result);
	}

	@Test
	@DisplayName("Add incorrect item in cart")
	void addItemError() {
		boolean result = cartService.addItem(new Item("SSD", 80, 0));
		assertFalse(result);
	}

	@Test
	@DisplayName("Remove correct item from cart")
	void removeItem() {
		boolean result = cartService.removeItem(new Item("CPU", 300, 1));
		assertTrue(result);
	}

	@Test
	@DisplayName("Remove incorrect item from cart")
	void removeItemError() {
		boolean result = cartService.removeItem(new Item("Banana", 300, 1));
		assertFalse(result);
	}

	@Test
	@DisplayName("Get all cart items")
	void getCartItems() {
		List<Item> items = new ArrayList<>();
		items.add(new Item("CPU", 300, 1));
		items.add(new Item("RAM", 120, 2));
		assertIterableEquals(items, cartService.getCartItems());
	}

	@Test
	@DisplayName("Calculate total cart price")
	void getTotalPrice() {
		assertEquals(540, cartService.getTotalPrice());
	}

	@Test
	@DisplayName("Checkout successfully")
	void checkout() {
		when(mockedPaymentService.balance()).thenReturn(550.0);
		//cart total price is 540
		boolean isCompleted = cartService.checkout();
		int numOfCartItems = cartService.getCartItems().size();
		assertAll(() -> assertTrue(isCompleted, "Checkout is NOT completed successfully"),
				() -> assertEquals(0, numOfCartItems, "Cart is NOT empty"));

		verify(mockedPaymentService).withdraw(540);
	}

	@Test
	@DisplayName("Checkout unsuccessful due to insufficient balance")
	void checkoutError() {
		when(mockedPaymentService.balance()).thenReturn(100.0);
		boolean isCompleted = cartService.checkout();
		int numOfCartItems = cartService.getCartItems().size();
		assertAll(() -> assertFalse(isCompleted, "Checkout is completed successfully"),
				() -> assertEquals(2, numOfCartItems, "Cart is empty"));
		verify(mockedPaymentService, never()).withdraw(540);
	}
}
