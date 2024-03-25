package com.bkhb.EchoSphere;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class EchoSphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(EchoSphereApplication.class, args);
	}

}
