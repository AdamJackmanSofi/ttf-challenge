package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by vthakor on 5/2/18.
 */
@SpringBootApplication
public class GiphySearchApplication implements CommandLineRunner {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(GiphySearchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //stub
    }
}
