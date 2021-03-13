package com.inzamamul.haque;

import com.inzamamul.haque.configuration.ImageStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		ImageStorageProperties.class
})
public class UploadImageBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadImageBackendApplication.class, args);
	}
}
