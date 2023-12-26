package andrehsvictor.api.dailydo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@SpringBootApplication
public class DailydoApplication {

	@GetMapping("/")
	public String index() {
		return "Welcome to Dailydo API!";
	}
	

	public static void main(String[] args) {
		SpringApplication.run(DailydoApplication.class, args);
	}

}
