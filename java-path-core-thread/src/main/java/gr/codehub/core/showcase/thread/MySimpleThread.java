package gr.codehub.core.showcase.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

public class MySimpleThread implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(MySimpleThread.class);

	//use 100 for join() example
	//use 10000 for interrupt() example
	private static long sleepTime = 100;
	private String name;

	public MySimpleThread(String name) {
		this.name = name;
		logger.info("{} created.", name);
	}

	@Override
	public void run() {
		logger.info("{} starting.", name);
		for (int i = 0; i < 10; i++) {
			logger.info("{} iterates at {}.", name, i);
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				logger.info("Someone interrupted me");
			}
		}
		logger.info("{} finished.", name);
	}

}

class MySimpleThreadWithStop implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(MySimpleThreadWithStop.class);

	private AtomicBoolean exit = new AtomicBoolean(false);
	private String name;

	public MySimpleThreadWithStop(String name) {
		this.name = name;
		logger.info("{} created.", name);
	}

	@Override
	public void run() {
		logger.info("{} starting.", name);
		while (!exit.get()){
			try {
				Thread.sleep(2000);
				logger.info("I will run till you stop me.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logger.info("Finally you stopped me.");
	}

	public void stop(){
		exit.set(true);
	}
}

class MySimpleThreadMain {
	public static void main(String[] args) {
		Thread t1 = new Thread(new MySimpleThread("Thread No.1"));
		Thread t2 = new Thread(new MySimpleThread("Thread No.2"));
		t1.start();
		t2.start();
	}
}

class MySimpleThreadMainWithJoin {
	private static final Logger logger = LoggerFactory.getLogger(MySimpleThreadMainWithJoin.class);
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new MySimpleThread("Thread No.1"));
		Thread t2 = new Thread(new MySimpleThread("Thread No.2"));
		Thread t3 = new Thread(new MySimpleThread("Thread No.3"));
		t1.start();
		logger.info("Current thread {} will start waiting.",Thread.currentThread().getName());
		t1.join();
		logger.info("Join invoked by thread {}",Thread.currentThread().getName());
		t2.start();
		logger.info("Current thread {} will start waiting for 1 sec.",Thread.currentThread().getName());
		t2.join(1000);
		logger.info("Join invoked by thread {} and 1 sec passed.",Thread.currentThread().getName());
		t3.start();
	}
}

class MySimpleThreadMainWithInterrupt {
	private static final Logger logger = LoggerFactory.getLogger(MySimpleThreadMainWithInterrupt.class);
	public static void main(String[] args) {
		Thread t1 = new Thread(new MySimpleThread("Thread No.1"));
		t1.start();
		try {
			Thread.sleep(5000);
			logger.info("Send an interrupt to stop sleeping.");
			t1.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class MySimpleThreadMainWithStop {
	public static void main(String[] args) {
		MySimpleThreadWithStop ts = new MySimpleThreadWithStop("Thread No.1");
		Thread t1 = new Thread(ts);
		t1.start();
		try {
			Thread.sleep(5000);
			ts.stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
