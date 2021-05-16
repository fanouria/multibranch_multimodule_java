package gr.codelearn.core.showcase.nestedclass.local;

import gr.codelearn.core.showcase.nestedclass.local.domain.OuterClass;
import gr.codelearn.core.showcase.nestedclass.local.domain.PasswordChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger logger = LoggerFactory
			.getLogger(gr.codelearn.core.showcase.nestedclass.nested.classes.Main.class);

	public static void main(String[] args) {
		// Outer class with local class basic example
		OuterClass outerClass = new OuterClass();
		outerClass.display();

		// Password validator with local class example
		boolean isPasswordValid = PasswordChecker.validatePassword("Ab3cdef@");
		if (isPasswordValid) {
			logger.info("Password is valid");
		} else {
			logger.info("Password is not valid");
		}
	}
}
