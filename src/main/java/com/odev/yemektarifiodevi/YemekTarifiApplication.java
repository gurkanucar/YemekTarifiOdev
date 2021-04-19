package com.odev.yemektarifiodevi;

import com.odev.yemektarifiodevi.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class YemekTarifiApplication {

	public static void main(String[] args) {
		SpringApplication.run(YemekTarifiApplication.class, args);
	}

}
