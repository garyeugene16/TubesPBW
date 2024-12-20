package com.example.TugasBesar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableAspectJAutoProxy
public class TugasBesarApplication {

	public static void main(String[] args) {
		SpringApplication.run(TugasBesarApplication.class, args);
	}

}
