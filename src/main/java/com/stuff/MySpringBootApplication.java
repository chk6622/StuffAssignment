package com.stuff;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MySpringBootApplication implements CommandLineRunner {


    public static void main(String[] args){
        SpringApplication app = new SpringApplication(MySpringBootApplication.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
