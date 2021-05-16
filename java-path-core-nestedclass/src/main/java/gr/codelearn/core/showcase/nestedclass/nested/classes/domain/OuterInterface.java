package gr.codelearn.core.showcase.nestedclass.nested.classes.domain;

public interface OuterInterface {
	// We can even have nested classes inside interfaces
	// The Inner Class is inherited by every other class/interface that implements the outer interface
	// Implicitly declared public and static
	class InnerClass {
		private final String string = "Hello from inside an outer interface";

		public String getString() {
			return string;
		}

		public static String getStaticString() {
			return "Hello statically from inside an outer interface";
		}
	}
}
