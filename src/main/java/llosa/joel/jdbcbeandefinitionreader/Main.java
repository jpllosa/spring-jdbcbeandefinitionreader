package llosa.joel.jdbcbeandefinitionreader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcBeanDefinitionReader;

@SpringBootApplication
public class Main implements CommandLineRunner {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	
	public void run(String... arg0) throws Exception {
		System.out.println("Building tables");
		jdbcTemplate.execute("DROP TABLE coffee_beans IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE coffee_beans(id SERIAL, beanName VARCHAR(255), property VARCHAR(255), value VARCHAR(255))");
		
		System.out.println("\nCreating the Spring Beans...");
		jdbcTemplate.update("INSERT INTO coffee_beans(beanName, property, value) VALUES (?, ?, ?)", "robusta", "(class)", "llosa.joel.jdbcbeandefinitionreader.CoffeeBean");
		jdbcTemplate.update("INSERT INTO coffee_beans(beanName, property, value) VALUES (?, ?, ?)", "robusta", "(abstract)", "false");
		jdbcTemplate.update("INSERT INTO coffee_beans(beanName, property, value) VALUES (?, ?, ?)", "robusta", "weight", "1");
		
		jdbcTemplate.update("INSERT INTO coffee_beans(beanName, property, value) VALUES (?, ?, ?)", "arabica", "(class)", "llosa.joel.jdbcbeandefinitionreader.Arabica"); // must be fully qualified
		jdbcTemplate.update("INSERT INTO coffee_beans(beanName, property, value) VALUES (?, ?, ?)", "arabica", "$0", "2"); // inject 2 as the constructor argument
		
		jdbcTemplate.update("INSERT INTO coffee_beans(beanName, property, value) VALUES (?, ?, ?)", "barako", "(class)", "llosa.joel.jdbcbeandefinitionreader.Barako");
		jdbcTemplate.update("INSERT INTO coffee_beans(beanName, property, value) VALUES (?, ?, ?)", "barako", "$0(ref)", "arabica"); // inject arabica bean as the 0th constructor argument
		jdbcTemplate.update("INSERT INTO coffee_beans(beanName, property, value) VALUES (?, ?, ?)", "barako", "$1(ref)", "robusta"); // inject robusta bean as the 0th constructor argument
		jdbcTemplate.update("INSERT INTO coffee_beans(beanName, property, value) VALUES (?, ?, ?)", "barako", "(lazy-init)", "true"); // default is false. lazy initialization: delay 'expensinve operation' until needed, store result so that 'expensive opearation isn't repeated
		
		readRecords();
		
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		JdbcBeanDefinitionReader beanReader = new JdbcBeanDefinitionReader(beanFactory);
		beanReader.setJdbcTemplate(jdbcTemplate);
		beanReader.loadBeanDefinitions("SELECT beanName, property, value FROM coffee_beans"); // we don't want to include id
		
		System.out.println();
		System.out.println("Number of Spring Beans in container: " + beanFactory.getBeanDefinitionCount());
		CoffeeBean robusta = (CoffeeBean) beanFactory.getBean("robusta");
		
		Seed arabica = (Seed) beanFactory.getBean("arabica");
		Seed barako = (Seed) beanFactory.getBean("barako");
		
		System.out.println("robusta: " + robusta);
		System.out.println("arabica: " + arabica);
		System.out.println("barako: " + barako);
	}
	
	private void readRecords() {
		System.out.println("Reading Spring Bean records...");
		System.out.printf("%-30.30s  %-30.30s %-30.30s%n", "Bean Name", "Property", "Value");
		jdbcTemplate.query("SELECT * FROM coffee_beans", new RowCallbackHandler() {

			public void processRow(ResultSet rs) throws SQLException {
				System.out.printf("%-30.30s  %-30.30s %-30.50s%n", rs.getString("beanName"), rs.getString("property"), rs.getString("value"));
			}
			
		});
	}
}
