package gr.codelearn.core.showcase.nestedclass.nested.classes;

import gr.codelearn.core.showcase.nestedclass.nested.classes.domain.OuterClass;
import gr.codelearn.core.showcase.nestedclass.nested.classes.domain.OuterInterface;
import gr.codelearn.core.showcase.nestedclass.nested.classes.domain.OuterInterfaceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		// Outer class instantiation
		OuterClass outerClass = new OuterClass();
		String concatenatedStr;

		// Public inner class example
		OuterClass.InnerClass innerClass = outerClass.new InnerClass();
		concatenatedStr = outerClass.getString() + " " + innerClass.getString();
		logger.info("The concatenated string is: {}", concatenatedStr);

		// Private inner class example (check inside method)
		concatenatedStr += outerClass.getStringFromPrivateInnerClass();
		logger.info("The concatenated string is: {}", concatenatedStr);

		// Static inner class example with non-static method, no outer class instantiation required
		OuterClass.StaticInnerClass staticInnerClass = new OuterClass.StaticInnerClass();
		concatenatedStr += " " + staticInnerClass.getString();
		logger.info("The concatenated string is: {}", concatenatedStr);

		// Static inner class example with static method, no class requires instantiation
		concatenatedStr = OuterClass.StaticInnerClass.getStaticString();
		logger.info("The concatenated string is: {}", concatenatedStr);

		// Example of how to access outer and inner fields (check inside method)
		concatenatedStr = innerClass.getConcatenatedString(":D");
		logger.info("The concatenated string is: {}", concatenatedStr);

		// "Usual" instantiation of a class and upcasting to its implemented interface
		OuterInterface outerInterface = new OuterInterfaceImpl();

		// Call to a static method
		concatenatedStr = OuterInterface.InnerClass.getStaticString();
		logger.info("The concatenated string is: {}", concatenatedStr);

		// Instantiation of a nested class inside an interface
		OuterInterface.InnerClass outerInterfaceInnerClass = new OuterInterface.InnerClass();
		concatenatedStr = outerInterfaceInnerClass.getString();
		logger.info("The concatenated string is: {}", concatenatedStr);

	}
}
