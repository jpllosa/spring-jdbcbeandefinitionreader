package llosa.joel.jdbcbeandefinitionreader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfiguration {

	@Bean(name="rob")
	public Seed robustaSeed() {
		return new Robusta("1");
	}
	
	@Bean
	public Seed arabica() {
		return new Arabica("2");
	}
	
	@Bean("barako")
	public Seed barakoSeed() {
		return new Barako("3");
	}
	
//	@Bean(name="robusta")
////	@Bean
//	public CoffeeBean robustaCoffeeBean() {
//		return new CoffeeBean("1");
//	}
//	
//	@Bean("arabica")
////	@Bean(name="arabica")
////	@Bean
//	public CoffeeBean arabicaCoffeeBean() {
//		return new CoffeeBean("2");
//	}
//	
//	@Bean(name="java")
////	@Bean
//	public CoffeeBean javaCoffeeBean() {
//		return new CoffeeBean("3");
//	}
//	
//	@Bean
//	public CoffeeBean barako() {
//		return new CoffeeBean("4");
//	}
}
