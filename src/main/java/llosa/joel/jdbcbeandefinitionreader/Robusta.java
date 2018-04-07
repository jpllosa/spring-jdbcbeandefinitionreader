package llosa.joel.jdbcbeandefinitionreader;

public class Robusta implements Seed {

	private String weight;
	
	public Robusta() {}
	
	public Robusta(String weight) {
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
		return "Robusta [weight=" + weight + "]";
	}

}
