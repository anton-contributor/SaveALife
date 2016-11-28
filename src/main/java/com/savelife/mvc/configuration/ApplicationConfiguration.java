package com.savelife.mvc.configuration;

import com.google.maps.GeoApiContext;
import com.savelife.mvc.apis.messaging.configuration.MassagingFireBaseContext;
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
    public GeoApiContext geoApiContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        geoApiContext.setApiKey("AIzaSyAaAfOrNHFkfsUYp6_uVprFmvctHeqAoT0");
        geoApiContext.setConnectTimeout(1, TimeUnit.SECONDS)
                .setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS);
        return geoApiContext;
    }

    @Bean
    public MassagingFireBaseContext massagingFireBaseContext() {
        MassagingFireBaseContext massagingFireBaseContext = new MassagingFireBaseContext();
        massagingFireBaseContext.setApiKey("AIzaSyD-ydqn5T5XUpshJMYAwHmeRyWB0aIEMAw");
        massagingFireBaseContext.setConnectionUrl("https://fcm.googleapis.com/fcm/send");
        return massagingFireBaseContext;
    }


}