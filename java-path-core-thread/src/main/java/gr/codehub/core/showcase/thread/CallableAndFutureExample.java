package gr.codehub.core.showcase.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class MyCallable implements Callable<String> {
	private static final Logger logger = LoggerFactory.getLogger(MyCallable.class);

	private String name;

	public MyCallable(String name) {
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		logger.info("Callable {} initiating.", name);
		Thread.sleep(5000);
		return "You called me " + name;
	}
}

public class CallableAndFutureExample {
	private static final Logger logger = LoggerFactory.getLogger(CallableAndFutureExample.class);

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<String> future1 = executorService.submit(new MyCallable("Callable 1"));

		while (!future1.isDone()) {
			logger.info("Waiting for an answer.");
		}

		try {
			//even if we did not used the loop above
			//the code will wait for future to return
			logger.info("Future returned {}", future1.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		executorService.shutdown();

		ExecutorService executorServicePool = Executors.newFixedThreadPool(4);
		List<Future<String>> futures = new ArrayList<>();
		futures.add(executorServicePool.submit(new MyCallable("Callable 1")));
		futures.add(executorServicePool.submit(new MyCallable("Callable 2")));
		futures.add(executorServicePool.submit(new MyCallable("Callable 3")));
		futures.add(executorServicePool.submit(new MyCallable("Callable 4")));

		futures.get(1).cancel(true);

		for (Future<String> future : futures) {
			if (!future.isCancelled()) {
				try {
					logger.info("{}", future.get(3, TimeUnit.SECONDS));
				} catch (InterruptedException | ExecutionException | TimeoutException e) {
					e.printStackTrace();
				}
			}
		}
		executorServicePool.shutdown();
		try {
			//wait(blocks) 10 seconds to shutdown,
			//if tasks finish earlier thus executor terminates
			//and returns true
			if (!executorServicePool.awaitTermination(10, TimeUnit.SECONDS)) {
				executorServicePool.shutdownNow();
			}
		} catch (InterruptedException ie) {
			// in case current thread was interrupted
			executorServicePool.shutdownNow();
		}
	}
}
