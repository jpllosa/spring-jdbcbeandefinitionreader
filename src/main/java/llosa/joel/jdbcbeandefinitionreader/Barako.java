package llosa.joel.jdbcbeandefinitionreader;

public class Barako implements Seed {

	private String weight;
	
	public Barako() {}
	
	public Barako(String weight) {
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
		return "Barako [weight=" + weight + "]";
	}

}
