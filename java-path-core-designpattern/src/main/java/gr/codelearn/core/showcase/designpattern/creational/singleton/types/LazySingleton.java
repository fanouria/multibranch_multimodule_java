package gr.codelearn.core.showcase.designpattern.creational.singleton.types;

public class LazySingleton {

	//lazy singleton, instantiated for the first time only when we call it
	private static LazySingleton instance = null;

	public static LazySingleton getInstance() {
		if (instance == null) {
			instance = new LazySingleton();
		}
		return instance;
	}
}
