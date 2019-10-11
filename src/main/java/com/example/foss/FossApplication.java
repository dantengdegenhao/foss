package com.example.foss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*@SpringCloudApplication
@EnableFeignClients*/
@SpringBootApplication
@MapperScan("com.example.foss.mapper")
public class FossApplication {

	public static void main(String[] args) {
		SpringApplication.run(FossApplication.class, args);
	}

}
