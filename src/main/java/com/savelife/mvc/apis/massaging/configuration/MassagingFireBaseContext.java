package com.savelife.mvc.apis.massaging.configuration;

import org.springframework.stereotype.Component;

/**
 * Created by anton on 20.08.16.
 */
@Component
public class MassagingFireBaseContext {

    /*
    * fire base project key
    * */
    private String apiKey;
    /*
    * basic url to send the massages to the fire base cloud service
    * */
    private String connectionUrl;

    public MassagingFireBaseContext() {
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = "key=" + apiKey;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }
}
