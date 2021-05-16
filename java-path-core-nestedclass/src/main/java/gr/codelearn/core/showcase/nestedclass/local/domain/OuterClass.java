package gr.codelearn.core.showcase.nestedclass.local.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OuterClass {
	private static final Logger logger = LoggerFactory
			.getLogger(gr.codelearn.core.showcase.nestedclass.nested.classes.Main.class);

	public void display() {
		// Method which contains a local class

		// Just like in normal methods, variables cannot have an access modifier
		// After JDK1.8, non-final local variables can also be declared
		int methodVariable = 20;

		// Local classes cannot be static and no access modifier
		// Since they are declared locally, their scope is only within this method
		class LocalClass {
			// Inner local variables can have an access modifier like normal classes
			int classVariable = 30;

			// Inner classes cannot have static declarations but they can have public or private access modifier
			// However, the access modifier plays no role
			private void message() {
				logger.info("Hello from local class inside a method inside a class! My value is {}",
						classVariable + methodVariable);
			}
		}
		// Note that re-assigning a method variable will result to a compiler error, stating that a variable needs to
		// be final
		//methodVariable = 30;

		// Instantiating the local class inside the method and calling the inner method
		LocalClass localClass = new LocalClass();
		localClass.message();
	}
}
