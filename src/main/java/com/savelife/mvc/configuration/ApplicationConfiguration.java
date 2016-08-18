package com.savelife.mvc.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.FileNotFoundException;

/**
 * Created by anton on 27.07.16.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.savelife.mvc")
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

    public ApplicationConfiguration() throws FileNotFoundException {
    }

}