package com.expense.config;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "com.expense")
@EntityScan( basePackages = {"com.expense"})
public class ActuatorServiceApplication {
	public static void main(String[] args) throws InvalidPasswordException, IOException {

		ApplicationContext ctx = SpringApplication.run(ActuatorServiceApplication.class, args);

	}
}
