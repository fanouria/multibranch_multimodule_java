package gr.codelearn.core.showcase.designpattern.creational.singleton.types;

public class EagerSingleton {

	//eager singleton, instantiated for the first time when our application runs for the first time
	//the final keyword ensures that this instance exists only once
	private static final EagerSingleton INSTANCE = new EagerSingleton();

	public static EagerSingleton getInstance() {
		return INSTANCE;
	}
}
