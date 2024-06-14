package gorgeousSandwich.promotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"gorgeousSandwich.promotion","gorgeousSandwich.promotion.Util"})
public class PromotionMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromotionMicroserviceApplication.class, args);
	}

}
