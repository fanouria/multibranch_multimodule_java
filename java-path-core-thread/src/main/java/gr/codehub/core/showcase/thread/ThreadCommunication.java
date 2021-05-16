package gr.codehub.core.showcase.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ThreadCommunication {
	private static final Logger logger = LoggerFactory.getLogger(ThreadCommunication.class);
	private int balance = 100;

	public synchronized void withdraw(int amount) {
		if (this.balance < amount) {
			logger.info("Insufficient balance({}) < withdraw amount({}).Waiting for deposit", balance, amount);
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("Balance is now sufficient for withdrawal.");
		this.balance -= amount;
		logger.info("Withdraw successful amount({}).Current balance:{}", amount, balance);
	}

	public synchronized void deposit(int amount) {
		this.balance += amount;
		logger.info("Deposit successful amount({}).Current balance:{}", amount, balance);
		notifyAll();
	}
}

class ThreadCommunicationMain {
	public static void main(String args[]) {
		ThreadCommunication tc = new ThreadCommunication();
		new Thread(() -> tc.withdraw(120)).start();
		new Thread(() -> tc.deposit(50)).start();
	}
}
