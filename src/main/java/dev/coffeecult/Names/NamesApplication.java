package dev.coffeecult.Names;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NamesApplication {
  @Value("${spring.datasource.password}")
  private static String pass;
	public static void main(String[] args) {
		SpringApplication.run(NamesApplication.class, args);
		System.out.println(pass);
	}

}
