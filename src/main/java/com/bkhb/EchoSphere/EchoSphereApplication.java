package com.bkhb.EchoSphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author bkhb
 */
@EnableAsync
@SpringBootApplication
public class EchoSphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(EchoSphereApplication.class, args);
	}

}
