package gr.codelearn.core.showcase.designpattern.creational.builder.notapplied;

public class Main {
	public static void main(String[] args) {
		//customer 1 wants a house that has a foundation, structure and roof
		House concreteHouse1 = new House("Concrete", "Reinforced Steel", "Ceramic");
		//customer 2 a similar house to customer 1, but furnished and painted
		House concreteHouse2 = new House("Concrete", "Reinforced Steel", "Ceramic", true, true);
		//customer 3 wants a wooden house, but does not care if its painted, but wants it furnished
		House woodenHouse1 = new House("Wood", "Wood", "Ceramic", true);
		//customer 4 simply wants the area that the house will be built on
		House house = new House();

		//we can see that we have created too many constructors, imagine if the House object was more complicated\
		//questions that need to be answered:
        /*
            Which constructor should I invoke?
            What will be the default values of the parameters if I don’t provide?
            Does the first boolean in constructor2 represent painting or furnishing?
         */
		//leads to telescopic constructor pattern which is an anti-pattern
	}
}
