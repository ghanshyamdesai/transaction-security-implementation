package com.nci.config;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "com.nci")
public class ActuatorServiceApplication {
	public static void main(String[] args) throws InvalidPasswordException, IOException {

		ApplicationContext ctx = SpringApplication.run(ActuatorServiceApplication.class, args);

	}
}
