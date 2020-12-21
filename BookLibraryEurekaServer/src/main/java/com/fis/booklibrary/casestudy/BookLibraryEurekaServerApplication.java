package com.fis.booklibrary.casestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BookLibraryEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookLibraryEurekaServerApplication.class, args);
	}

}
