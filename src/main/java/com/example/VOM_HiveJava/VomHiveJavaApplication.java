package com.example.VOM_HiveJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class VomHiveJavaApplication {


	public static void main(String[] args) {
		SpringApplication.run(VomHiveJavaApplication.class, args);
	}

	@GetMapping(value = "/")
	public String index(){
		return "VOM-HIVE";
	}
}
