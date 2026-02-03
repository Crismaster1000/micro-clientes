package com.stephano.microclientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MicroClientesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroClientesApplication.class, args);
    }

    @GetMapping("/")
    public String helloWorld() {
        return "Hola Mundo desde micro-clientes";
    }

}
