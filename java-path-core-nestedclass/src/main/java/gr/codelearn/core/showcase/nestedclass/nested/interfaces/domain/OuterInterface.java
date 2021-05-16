package gr.codelearn.core.showcase.nestedclass.nested.interfaces.domain;

public interface OuterInterface {

	// Not required to be implemented by classes/interfaces that implemented the nested interfaces of this interface
	String method1();

	// Nested interfaces are public and static implicitly
	interface NestedInterface1 {
		String method1();
	}

	interface NestedInterface2 {
		String method1();

		String method2();

		String method3();
	}
}
