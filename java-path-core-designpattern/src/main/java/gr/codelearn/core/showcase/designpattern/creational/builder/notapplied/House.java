package gr.codelearn.core.showcase.designpattern.creational.builder.notapplied;

public class House {
	private String foundation;
	private String structure;
	private String roof;
	private boolean furnished;
	private boolean painted;

	public House() {
	}

	public House(String foundation, String structure, String roof) {
		this.foundation = foundation;
		this.structure = structure;
		this.roof = roof;
	}

	public House(String foundation, String structure, String roof, boolean furnished, boolean painted) {
		this.foundation = foundation;
		this.structure = structure;
		this.roof = roof;
		this.furnished = furnished;
		this.painted = painted;
	}

	public House(String foundation, String structure, String roof, boolean furnished) {
		this.foundation = foundation;
		this.structure = structure;
		this.roof = roof;
		this.furnished = furnished;
	}

	public void setFoundation(String foundation) {
		this.foundation = foundation;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public void setRoof(String roof) {
		this.roof = roof;
	}

	public void setFurnished(boolean furnished) {
		this.furnished = furnished;
	}

	public void setPainted(boolean painted) {
		this.painted = painted;
	}

	@Override
	public String toString() {
		return "Foundation - " + foundation + " Structure - " + structure + " Roof - " + roof + " Is Furnished? " +
				furnished + " Is Painted? " + painted;
	}
}
