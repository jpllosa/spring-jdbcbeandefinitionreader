package llosa.joel.jdbcbeandefinitionreader;

// TODO: change to an interface
public class CoffeeBean {

	private String weight;
	
	public CoffeeBean() {}
	
	public CoffeeBean(String weight) {
		this.weight = weight;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoffeeBean other = (CoffeeBean) obj;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CoffeeBean [weight=" + weight + "]";
	}
	
}
