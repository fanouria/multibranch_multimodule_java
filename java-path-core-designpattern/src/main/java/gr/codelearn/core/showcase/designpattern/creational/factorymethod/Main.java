package gr.codelearn.core.showcase.designpattern.creational.factorymethod;

import gr.codelearn.core.showcase.designpattern.creational.factorymethod.domain.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//client
public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		//we see that with a factory, we avoid the usage of "new" to create our objects and
		//object-creation is all centralised in one location
		ToastFactory toastFactory = new ToastFactory();

		Toast peanutButterToast = toastFactory.createToast("peanut-butter");
		logger.info("{}", peanutButterToast);

		Toast tunaToast = toastFactory.createToast("tuna");
		logger.info("{}", tunaToast);
	}
}
