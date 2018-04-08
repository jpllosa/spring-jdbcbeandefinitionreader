package llosa.joel.jdbcbeandefinitionreader;

public class Barako implements Seed {

	private String weight;
	
	public Barako(Arabica w1, CoffeeBean w2) {
		setWeight(w1.getWeight() + w2.getWeight());
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
