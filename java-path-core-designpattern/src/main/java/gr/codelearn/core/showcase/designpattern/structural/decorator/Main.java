package gr.codelearn.core.showcase.designpattern.structural.decorator;

import gr.codelearn.core.showcase.designpattern.structural.decorator.domain.FlowerBouquet;
import gr.codelearn.core.showcase.designpattern.structural.decorator.domain.GlitterDecorator;
import gr.codelearn.core.showcase.designpattern.structural.decorator.domain.RoseBouquet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//client
public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {

		//Rose bouquet with no decoration
		FlowerBouquet roseBouquet = new RoseBouquet();
		logger.info("{} {}€", roseBouquet.getDescription(), roseBouquet.cost());

		//decorating with new functionality:
		FlowerBouquet decoratedRoseBouquet = new GlitterDecorator(roseBouquet);
		logger.info("{} {}€", decoratedRoseBouquet.getDescription(), decoratedRoseBouquet.cost());
	}
}
