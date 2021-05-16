package gr.codelearn.core.showcase.designpattern.creational.singleton.types;

/*
If we have two threads, and both of them call the getInstance()
method, it is possible that two individual objects of the same class get created, due to different times of accessing
the (instance==null) check
 */
public class ThreadSafeSingleton {

	private static LazySingleton instance = null;

	//thread safe but adds performance cost overhead that may not be wanted
	public synchronized static LazySingleton getInstance() {
		if (instance == null) {
			instance = new LazySingleton();
		}
		return instance;
	}
}
