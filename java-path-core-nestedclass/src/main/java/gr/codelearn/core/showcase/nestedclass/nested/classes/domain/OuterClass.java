package gr.codelearn.core.showcase.nestedclass.nested.classes.domain;

public class OuterClass {
	public final String string = "Hello";

	public String getString() {
		return string;
	}

	public String getStringFromPrivateInnerClass() {
		// Private inner class can only be accessed from within the outer class
		PrivateInnerClass privateInnerClass = new PrivateInnerClass();
		// The String can also be accessed without the getter method
		return privateInnerClass.getString();
	}

	// Inner public class that can be instantiated like a normal object
	public class InnerClass {
		private final String string = "World";

		public String getString() {
			return string;
		}

		public String getConcatenatedString(String string) {
			// How to access inner/outer variables with the same name?
			// OuterClass.this references the outer class, this references the inner class and string references the
			// method parameter
			return OuterClass.this.string + " " + this.string + " " + string;
		}
	}

	// Inner private class, cannot be accessed from outside
	private class PrivateInnerClass {
		private final String string = "!";

		public String getString() {
			return string;
		}
	}

	// Inner static class that does not require instantiation of the outer class
	// Cannot access non-static fields unless instantiated
	public static class StaticInnerClass {
		private final String string = "What is up?";

		// In order to use this method, you are required to instantiate the inner class
		public String getString() {
			return string;
		}

		// In order to use this method, you are NOT required to instantiate the inner class
		public static String getStaticString() {
			return "Hello from a static world";
		}
	}
}
