package com.savelife.mvc.configuration;

import com.google.maps.GeoApiContext;
import com.savelife.mvc.apis.massaging.configuration.MassagingFireBaseContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

/**
 * Created by anton on 27.07.16.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.savelife.mvc")
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

    public ApplicationConfiguration() throws FileNotFoundException {
    }

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
    public MassagingFireBaseContext massagingFireBaseContext(){
        MassagingFireBaseContext massagingFireBaseContext = new MassagingFireBaseContext();
        massagingFireBaseContext.setApiKey("AIzaSyBJ6NYpCY-y3dhVCtnbPaNyBGn2oetce5M");
        massagingFireBaseContext.setConnectionUrl("https://fcm.googleapis.com/fcm/send");
        return massagingFireBaseContext;
    }

}