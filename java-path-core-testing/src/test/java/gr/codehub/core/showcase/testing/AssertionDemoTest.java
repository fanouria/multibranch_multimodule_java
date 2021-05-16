package gr.codehub.core.showcase.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AssertionDemoTest {
	private static final Logger logger = LoggerFactory.getLogger(AssertionDemoTest.class);

	@Test
	void Example1() {
		//the test will fail no matter what
		fail("This test will fail");
	}

	@Test
	void Example2() {
		int expected = 1;
		int actual = 1;
		//assert equality
		assertEquals(expected, actual);
	}

	@Test
	void Example3() {
		int expected = 1;
		int actual = 2;
		//assert equality with error message
		assertEquals(expected, actual, "Fail message!");
	}

	@Test
	void Example4() {
		boolean[] expected = {true, false, true};
		boolean[] actual = {true, true, false};
		//assert that the arrays are equal
		assertArrayEquals(expected, actual);
	}

	@Test
	void Example5() {
		String str1 = "Hello";
		//assert that the test is true
		assertTrue(str1.length() > 4, str1 + " must contain more than 4 characters");
	}

	@Test
	void Example6() {
		//assert all,it outputs each fail
		assertAll("Hulk powers", () -> assertEquals("Green", "Green"), () -> assertEquals("Hummer", "Bow"));
	}

	@Test
	void Example7() {
		//assert that it throws an exception
		assertThrows(ArithmeticException.class, () -> {int division = 1 / 0;});
	}

	@Test
	void Example8() {
		Object obj = null;
		//assert that object is null
		assertNull(obj);
	}

	@Test
	void Example9() {
		//assert that a functionality will
		//not exceed the specified amount of time
		assertTimeout(ofMillis(1), () -> {
			//some time consuming tasks
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(100);
			int[] myArray = new int[randomInt];
			for (int i = 0; i < randomInt; i++) {
				myArray[i] = randomGenerator.nextInt(100);
			}
			String myString = "Hello World";
			for (int i = 0; i < 100; i++) {
				myString += " Hello World";
			}
			String myStringModified = myString.replace("World", "World!!!");
			logger.info("{} ", myStringModified);
		});
	}

	@Test
	@DisplayName("The following test assumes a value is greater than 5")
	void Example10() {
		int numberOfRowsInDB = 100;
		//the condition must be true so the rest of assertions can be executed
		assumeTrue(numberOfRowsInDB > 5);
		assertEquals(100, numberOfRowsInDB);
	}

	@Test
	@Disabled("Implement when transaction service is Tested")
	@DisplayName("This test is disabled")
	void Example11() {
		fail();
	}

	@Test
	@DisplayName("This test must not run on Windows OS")
	@DisabledOnOs(OS.WINDOWS)
	void Example12() {
		Object kali = new Object();
		Object centos = new Object();
		//assert objects are same
		assertSame(kali, centos);
	}

	@RepeatedTest(5)
	@DisplayName("The following test will be repeated 5 times")
	void Example13(RepetitionInfo repetitionInfo) {
		logger.info("Current repetition:{} ", repetitionInfo.getCurrentRepetition());
		Random random = new Random();
		assertEquals(3, random.nextInt(5));
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 3, 6, 8, 11})
	@DisplayName("This is a parameterized test and checks for even numbers")
	void Example14(int value) {
		assertEquals(0, value % 2, value + " is not even");
	}

	private static Stream<Arguments> numberOfUsers() {
		return Stream.of(arguments(2, Arrays.asList("Tim Berners-Lee", "Alan Turing")),
				arguments(1, Arrays.asList("Linus Torvalds")),
				arguments(3, Arrays.asList("Barbara Liskov", "Charles Babbage", "Dennis Ritchie")));
	}

	@ParameterizedTest
	@MethodSource("numberOfUsers")
	@DisplayName("This parameterized test gets a of stream arguments")
	void Example15(int number, List<String> csList) {
		assertEquals(number, csList.size());
	}

	@Test
	@Tag("development")
	@DisplayName("This test includes a development tag")
	void Example16() {
		assertTrue(true);
	}

	@Test
	@Tag("production")
	@DisplayName("This test includes a production tag")
	void Example17() {
		assertTrue(true);
	}
}
