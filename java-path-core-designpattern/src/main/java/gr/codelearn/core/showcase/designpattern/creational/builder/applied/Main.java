package gr.codelearn.core.showcase.designpattern.creational.builder.applied;

public class Main {
	public static void main(String[] args) {
		//compared to the not-applied case, we can see how easy it is to "build" objects the way we want them without
		//the use of excessive constructors:

		House concreteHouse1 = new House.Builder("Concrete", "Reinforced Steel").setRoof("Ceramic").build();

		House concreteHouse2 = new House.Builder("Concrete", "Reinforced Steel").setRoof("Ceramic").setFurnished(true)
				.setPainted(true).build();

		House woodenHouse1 = new House.Builder("Wood", "Wood").setRoof("Ceramic").setFurnished(true).build();
	}
}
