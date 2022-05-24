package com.pleshkov.smartcontractgenerator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SmartContractGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartContractGeneratorApplication.class, args);
	}
}
