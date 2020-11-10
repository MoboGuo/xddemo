package com.mobo.xddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 *
 * @author Mobo
 */
@SpringBootApplication
@ServletComponentScan
public class XddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(XddemoApplication.class, args);
    }

}
