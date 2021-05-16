package gr.codelearn.core.showcase.designpattern.creational.factorymethod;

import gr.codelearn.core.showcase.designpattern.creational.factorymethod.domain.CheeseToast;
import gr.codelearn.core.showcase.designpattern.creational.factorymethod.domain.Toast;
import gr.codelearn.core.showcase.designpattern.creational.factorymethod.domain.TunaToast;

//concrete creator
public class ToastFactory {
	public Toast createToast(String type) {
		Toast toast;
		switch (type.toLowerCase()) {
			case "tuna":
				toast = new TunaToast();
				break;
			//other cases
			default:
				toast = new CheeseToast();
		}
		toast.addIngredients();
		toast.heatToast();
		return toast;
	}
}
