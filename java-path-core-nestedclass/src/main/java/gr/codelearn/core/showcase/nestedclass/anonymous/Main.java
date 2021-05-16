package gr.codelearn.core.showcase.nestedclass.anonymous;

import gr.codelearn.core.showcase.nestedclass.anonymous.domain.AnonymousInterface;
import gr.codelearn.core.showcase.nestedclass.util.MockCreation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

public class Main {
	private static final Logger logger = LoggerFactory
			.getLogger(gr.codelearn.core.showcase.nestedclass.nested.classes.Main.class);

	public static void main(String[] args) {
		// How to declare an anonymous class (check in method)
		String response;
		response = anonymousClassDeclaration();
		logger.info("Response from object: {}", response);

		// Thread example
		initializeThread();

		// Runnable example
		initializeRunnable();

		// Sorting example
		List<String> stringList = MockCreation.randomStrings(50);
		sortStrings(stringList);
		for (String string : stringList) {
			logger.info("{}", string);
		}
	}

	private static String anonymousClassDeclaration() {
		// This variable is in the scope of the anonymous class
		// After JDK1.8, non-final local variables can also be declared and accessed by the anonymous class
		int integer = 3;
		// Declaring an anonymous interface and implementing its methods
		AnonymousInterface anonymousInterface = new AnonymousInterface() {
			// Fields cannot be static
			final int integer2 = 5;

			@Override
			public String method1() {
				return "method1";
			}

			@Override
			public String method2() {
				return "method2" + integer;
			}

			@Override
			public String method3() {
				return "method3" + integer2;
			}
		};
		// Note that re-assigning a method variable will result to a compiler error, stating that a variable needs to
		// be final
		//integer = 5;
		return anonymousInterface.method2();
	}

	public static void initializeThread() {
		// Anonymous class that extends the Thread class
		// Suppose that we need an immediate thread but we don’t want to create a class that extends the Thread class
		// Each time you run it, the order that the child/main thread appear on the console differ
		Thread t = new Thread() {
			public void run() {
				logger.info("Hello from child thread");
			}
		};
		t.start();
		logger.info("Hello from main thread");
	}

	public static void initializeRunnable() {
		// Anonymous class that extends the Runnable interface
		// Similar to the thread example
		// Each time you run it, the order that the child/main thread appear on the console differ
		Runnable r = new Runnable() {
			public void run() {
				logger.info("Hello from child thread");
			}
		};
		Thread t = new Thread(r);
		t.start();
		logger.info("Hello from main thread");
	}

	public static void sortStrings(List<String> stringList) {
		stringList.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// By swapping o1 and o2, we can define the order (ascending/descending)
				return o2.compareTo(o1);
			}
		});
	}
}
