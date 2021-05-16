package gr.codehub.core.showcase.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

class Task implements Runnable{
	private static final Logger logger = LoggerFactory.getLogger(Task.class);
	private String name;

	public Task(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		logger.info("{} is starting for {}.", Thread.currentThread().getName(), name);
		logger.info("Initiating database connection for {}.", name);
		try {
			Thread.sleep(2000);
			logger.info("Retrieving information for {}.", name);
		} catch (InterruptedException e) {
			e.printStackTrace();
			logger.info("Connection to database was interrupted.");
		}
		logger.info("Information retrieved for {}.", name);
	}
}

class ExecutorServiceExampleMain {
	private static final Logger logger = LoggerFactory.getLogger(ExecutorServiceExampleMain.class);

	public static void main(String[] args) {
		Task task1 = new Task("Task 1");
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(task1);
		executorService.execute(()->{
			logger.info("Doing some tasks.");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("Finished the tasks.");
		});
		executorService.shutdown();

		Task task2 = new Task("Task 2");
		Task task3 = new Task("Task 3");
		Task task4 = new Task("Task 4");
		ExecutorService executorServicePool = Executors.newFixedThreadPool(2);
		executorServicePool.execute(task2);
		executorServicePool.execute(task3);
		executorServicePool.execute(task4);
		executorServicePool.shutdown();
		//executorService.shutdownNow(); will terminate immediately

		int corePoolSize = 1;
		int maxPoolSize = 3;
		int keepAliveTime = 5;
		TimeUnit timeUnit = TimeUnit.SECONDS;
		BlockingQueue<Runnable> workQueue = new SynchronousQueue<>();
		ThreadPoolExecutor threadPoolExecutor =
				new ThreadPoolExecutor(
						corePoolSize,
						maxPoolSize,
						keepAliveTime,
						timeUnit,
						workQueue
				);
		Task task5 = new Task("Task 5");
		Task task6 = new Task("Task 6");
		Task task7 = new Task("Task 7");
		threadPoolExecutor.execute(task5);
		threadPoolExecutor.execute(task6);
		threadPoolExecutor.execute(task7);
		threadPoolExecutor.shutdown();
	}
}
