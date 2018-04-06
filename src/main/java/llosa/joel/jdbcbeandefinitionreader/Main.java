package llosa.joel.jdbcbeandefinitionreader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

@SpringBootApplication
public class Main implements CommandLineRunner {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	CoffeeBean robusta;
	
	@Autowired
	CoffeeBean arabica;
	
	@Autowired
	CoffeeBean java;
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	
	public void run(String... arg0) throws Exception {
		System.out.println("Building tables");
		jdbcTemplate.execute("DROP TABLE coffee_beans IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE coffee_beans(id SERIAL, name VARCHAR(255), property VARCHAR(255), value VARCHAR(255))");
		
		System.out.println("\nCreating 3 coffee bean records...");
		jdbcTemplate.update("INSERT INTO coffee_beans(name, property, value) VALUES (?, ?, ?)", "robusta", "weight", "1");
		jdbcTemplate.update("INSERT INTO coffee_beans(name, property, value) VALUES (?, ?, ?)", "arabica", "weight", "2");
		jdbcTemplate.update("INSERT INTO coffee_beans(name, property, value) VALUES (?, ?, ?)", "java", "weight", "3");
		
		readRecords();
		
		
	}
	
	private void readRecords() {
		System.out.println("Reading movie records...");
		System.out.printf("%-30.30s  %-30.30s %-30.30s%n", "Name", "Property", "Value");
		jdbcTemplate.query("SELECT * FROM coffee_beans", new RowCallbackHandler() {

			public void processRow(ResultSet rs) throws SQLException {
				System.out.printf("%-30.30s  %-30.30s %-30.30s%n", rs.getString("name"), rs.getString("property"), rs.getString("value"));
			}
			
		});
	}
	
	@Bean(name="robusta")
	public CoffeeBean robustaCoffeeBean() {
		return new CoffeeBean("1");
	}
	
	@Bean(name="arabica")
	public CoffeeBean arabicaCoffeeBean() {
		return new CoffeeBean("2");
	}
	
	@Bean(name="java")
	public CoffeeBean javaCoffeeBean() {
		return new CoffeeBean("3");
	}

}
