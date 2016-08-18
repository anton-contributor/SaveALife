package com.savelife.mvc.apis.massaging;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by anton on 11.08.16.
 */
@Component
public class FireBaseConnection implements MassagingConnection,AbstractHttpConnection<String> {

    /*
    * fire base project key
    * */
    private final String fireBaseAppKey = "key=AIzaSyBJ6NYpCY-y3dhVCtnbPaNyBGn2oetce5M";

    /*
    * basic url to send the massages to the fire base cloud service
    * */
    private final String basicSendUrl = "https://fcm.googleapis.com/fcm/send";

    @Override
    public List<String> echo(List<String> body) {

        List<String> responses = new LinkedList<>();

            body.forEach((k)->{
                try {
                    responses.add(postByURLJsonBody(basicSendUrl,fireBaseAppKey,k));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });

        return responses;
    }


}
