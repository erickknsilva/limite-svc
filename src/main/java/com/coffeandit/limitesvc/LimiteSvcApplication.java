package com.coffeandit.limitesvc;

import com.coffeandit.limitesvc.repository.LimiteDiario;
import com.coffeandit.limitesvc.repository.LimiteDiarioRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class LimiteSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimiteSvcApplication.class, args);
	}

}
