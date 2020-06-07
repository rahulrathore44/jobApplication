package com.lab.jobportal;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@EntityScan(basePackages = {"com.lab.jobportal.users"})
public class JobPortal {
	private static final Logger oLog = LoggerFactory.getLogger(JobPortal.class);

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	public static void main(String[] args) {
		int port = 9191;
		try {
			port = Integer.parseInt(System.getProperty("server.port"));
		} catch (Exception e) {
			oLog.error(String.format("Invalid Port Number/Port already in use %s", System.getProperty("server.port")));
		}
		SpringApplication app = new SpringApplication(JobPortal.class);
		oLog.info(String.format("Application is Running on Port %s", port));
		app.setDefaultProperties(Collections.singletonMap("server.port", port));
		app.run(args);
	}

}
