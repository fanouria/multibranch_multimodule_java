package gr.codelearn.core.showcase.nestedclass.nested.interfaces.domain;

public class NestedInterface2Impl implements OuterInterface.NestedInterface2 {
	// Methods from the nested interface 2 are inherited

	@Override
	public String method1() {
		return "method1";
	}

	@Override
	public String method2() {
		return "method2";
	}

	@Override
	public String method3() {
		return "method3";
	}
}
