package itAcademy.giraffeMT.giraffeMT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication

@EntityScan({"com.company.banksystem.entity", "itAcademy.giraffeMT.giraffeMT.entities"})
public class GiraffeMtApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiraffeMtApplication.class, args);
	}

}
