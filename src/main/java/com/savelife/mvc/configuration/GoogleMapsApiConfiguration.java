package com.savelife.mvc.configuration;

import com.google.maps.GeoApiContext;
import com.savelife.mvc.controller.GoogleMapsApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.TimeUnit;

/**
 * Created by gleb-pc on 8/18/16.
 */
@Configuration
//@EnableTransactionManagement
public class GoogleMapsApiConfiguration {

    @Bean
    public GeoApiContext geoApiContext(){
        GeoApiContext geoApiContext = new GeoApiContext();
        geoApiContext.setApiKey("AIzaSyDm5ufZDaINAPv4BF8NjC7hEK9Nsno1ocE");
        geoApiContext.setConnectTimeout(1, TimeUnit.SECONDS)
                .setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS);
        return geoApiContext;
    }

    @Bean
    public GoogleMapsApi googleMapsApi(){
        return new GoogleMapsApi();
    }
}
