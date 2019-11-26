package com.example.demo;

import com.example.demo.Service.CallCommandCSV;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx= SpringApplication.run(DemoApplication.class, args);
        CallCommandCSV callCommandCSV=ctx.getBean(CallCommandCSV.class);
        callCommandCSV.csvBatch();

    }

}
