package ie.atu.modugrip_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ModugripBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModugripBackendApplication.class, args);
	}

}
