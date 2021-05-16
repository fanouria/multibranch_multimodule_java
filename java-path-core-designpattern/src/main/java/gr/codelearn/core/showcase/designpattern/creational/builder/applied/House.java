package gr.codelearn.core.showcase.designpattern.creational.builder.applied;

public class House {
	private String foundation;
	private String structure;
	private String roof;
	private boolean furnished;
	private boolean painted;

	private House(Builder builder) {
		this.setFoundation(builder.foundation);
		this.setStructure(builder.structure);
		this.setRoof(builder.roof);
		this.setFurnished(builder.furnished);
		this.setPainted(builder.painted);
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

	//normally in another class on its own
	public static final class Builder {
		private String foundation;
		private String structure;
		private String roof;
		private boolean furnished;
		private boolean painted;

		public Builder(String foundation, String structure) {
			//"mandatory" fields
			this.foundation = foundation;
			this.structure = structure;
		}

		public Builder setRoof(String roof) {
			//"optional" field
			this.roof = roof;
			return this;
		}

		public Builder setFurnished(boolean furnished) {
			//"optional" field
			this.furnished = furnished;
			return this;
		}

		public Builder setPainted(boolean painted) {
			//"optional" field
			this.painted = painted;
			return this;
		}

		public House build() {
			return new House(this);
		}

	}

	@Override
	public String toString() {
		return "Foundation - " + foundation + " Structure - " + structure + " Roof - " + roof + " Is Furnished? " +
				furnished + " Is Painted? " + painted;
	}
}
