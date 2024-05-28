package com.demo.gram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GramApplication {

	public static void main(String[] args) {
		SpringApplication.run(GramApplication.class, args);
		System.out.println("서버 준비 완료");
	}

}
