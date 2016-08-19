package com.savelife.mvc.controller;

import com.savelife.mvc.configuration.GoogleMapsApiConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by gleb-pc on 8/18/16.
 */
@SpringBootApplication
public class TestMainClass {

    @Autowired
    private static GoogleMapsApi googleMapsApi;

    public static void main(String[] args) {
        SpringApplication.run(TestMainClass.class, args);
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(GoogleMapsApiConfiguration.class);
//
//        GoogleMapsApi googleMapsApi = ctx.getBean(GoogleMapsApi.class   );
        if(googleMapsApi.getGeoApiContext() != null)
            System.out.println("lala");
        googleMapsApi.getRoute(50.459351, 30.514641, 50.455229, 30.512014);
    }

}
