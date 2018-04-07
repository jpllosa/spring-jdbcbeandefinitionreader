package llosa.joel.jdbcbeandefinitionreader;

public class Arabica implements Seed {

	private String weight;
	
	public Arabica() {}
	
	public Arabica(String weight) {
		setWeight(weight);
	}
	
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	@Override
	public String getWeight() {
		return this.weight;
	}

	@Override
	public String toString() {
		return "Arabica [weight=" + weight + "]";
	}

}
