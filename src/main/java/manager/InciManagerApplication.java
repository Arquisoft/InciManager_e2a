package manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

@SpringBootApplication
public class InciManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InciManagerApplication.class, args);
	}
}