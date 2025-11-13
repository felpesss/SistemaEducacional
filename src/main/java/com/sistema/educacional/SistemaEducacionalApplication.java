package com.sistema.educacional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;

@SpringBootApplication(exclude = {
    MongoAutoConfiguration.class,
    MongoDataAutoConfiguration.class
})
public class SistemaEducacionalApplication {
    public static void main(String[] args) {
        SpringApplication.run(SistemaEducacionalApplication.class, args);
    }
}
