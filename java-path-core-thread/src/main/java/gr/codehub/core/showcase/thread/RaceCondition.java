package gr.codehub.core.showcase.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RaceCondition implements Runnable{
	private static final Logger logger = LoggerFactory.getLogger(RaceCondition.class);

	private int totalValue;

	public RaceCondition() {
		totalValue = 0;
	}

	//remove synchronize to create the problem
	public synchronized void increment() {
		try {
			Thread.sleep(10);
			totalValue++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		logger.info("{} total value BEFORE increment is:{}", Thread.currentThread().getName(), totalValue);
		increment();
		logger.info("{} total value AFTER increment is:{}", Thread.currentThread().getName(), totalValue);
	}
}

class RaceConditionMain {
	public static void main(String[] args) {
		RaceCondition raceCondition = new RaceCondition();
		Thread t1 = new Thread(raceCondition);
		Thread t2 = new Thread(raceCondition);
		Thread t3 = new Thread(raceCondition);

		t1.start();
		t2.start();
		t3.start();
	}
}
