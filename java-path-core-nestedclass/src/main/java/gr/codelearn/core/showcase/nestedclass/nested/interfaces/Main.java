package gr.codelearn.core.showcase.nestedclass.nested.interfaces;

import gr.codelearn.core.showcase.nestedclass.nested.interfaces.domain.NestedInterface2Impl;
import gr.codelearn.core.showcase.nestedclass.nested.interfaces.domain.NestedInterfaceImpl;
import gr.codelearn.core.showcase.nestedclass.nested.interfaces.domain.OuterClass;
import gr.codelearn.core.showcase.nestedclass.nested.interfaces.domain.OuterInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger logger = LoggerFactory
			.getLogger(gr.codelearn.core.showcase.nestedclass.nested.classes.Main.class);

	public static void main(String[] args) {
		String response;

		//NestedInterface2Impl class upcasted to OuterInterface.NestedInterface2
		OuterInterface.NestedInterface2 outerInterfaceNestedInterface2 = new NestedInterface2Impl();
		response = outerInterfaceNestedInterface2.method3();
		logger.info("Response from object: {}", response);

		//NestedInterfaceImpl class upcasted to OuterClass.NestedInterface
		OuterClass.NestedInterface outerClassNestedInterface = new NestedInterfaceImpl();
		response = outerClassNestedInterface.method1();
		logger.info("Response from object: {}", response);
	}
}
