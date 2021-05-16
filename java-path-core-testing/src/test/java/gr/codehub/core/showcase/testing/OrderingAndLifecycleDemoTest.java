package gr.codehub.core.showcase.testing;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderingAndLifecycleDemoTest {
	private static final Logger logger = LoggerFactory.getLogger(OrderingAndLifecycleDemoTest.class);

	static Integer aValue = 5;

	@BeforeAll
	static void startUp() {
		//this will run at the very beginning
		logger.info("Starting the process with value:{} ", aValue);
	}

	@AfterAll
	static void closeUp() {
		logger.info("Ending the process with value:{} ", aValue);
	}

	@Test
	@Order(1)
	void Example1() {
		aValue++;
		logger.info("Value is:{} ", aValue);
		assertEquals(6, aValue);
	}

	@Test
	@Order(2)
	void Example2() {
		logger.info("Value is:{} ", aValue);
		assertEquals(5, aValue);
	}

	@Nested
	@DisplayName("this is a nested class")
	class NestedClass {

		String myString;

		@BeforeEach
		void initializeString() {
			logger.info("Initializing string");
			myString = "Hello World";
		}

		@Test
		@DisplayName("Clear the content of string")
		void clearString() {
			logger.info("Clearing string");
			myString = "";
			assertTrue(myString.isEmpty());
		}

		@Test
		@DisplayName("Check string length")
		void checkSize() {
			logger.info("Checking the length of string");
			String newString = myString.replace("World", "");
			assertEquals(6, newString.length());
		}

		@AfterEach
		public void clean() {
			logger.info("A clean up process");
		}
	}
}
