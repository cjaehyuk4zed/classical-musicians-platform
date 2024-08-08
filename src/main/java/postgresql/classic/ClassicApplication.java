package postgresql.classic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"postgresql.classic"})
@SpringBootApplication
public class ClassicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassicApplication.class, args);
	}

}