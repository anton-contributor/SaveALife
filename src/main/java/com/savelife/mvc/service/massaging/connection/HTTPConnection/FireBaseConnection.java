package com.savelife.mvc.service.massaging.connection.HTTPConnection;


import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * Created by anton on 11.08.16.
 */
@Component
public class FireBaseConnection implements MassagingConnection,AbstractHttpConnection<String>{

    /*
    * fire base project key
    * */
    private final String fireBaseAppKey = "key=AIzaSyBJ6NYpCY-y3dhVCtnbPaNyBGn2oetce5M";

    /*
    * basic url to send the massages to the fire base cloud service
    * */
    private final String basicSendUrl = "https://fcm.googleapis.com/fcm/send";

    @Override
    public String echo(String body) throws UnsupportedEncodingException {
        return postByURLJsonBody(basicSendUrl,fireBaseAppKey,body);
    }


}
