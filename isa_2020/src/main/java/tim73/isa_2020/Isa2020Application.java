package tim73.isa_2020;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Isa2020Application {

	public static void main(String[] args) {
		SpringApplication.run(Isa2020Application.class, args);
	}

}
