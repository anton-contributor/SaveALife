package com.savelife.mvc.apis.messaging;

import com.savelife.mvc.apis.messaging.configuration.MassagingFireBaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by anton on 11.08.16.
 */
@Component
public class FireBaseConnection implements MassageSender, AbstractHttpConnection<String> {

    private static Logger logger = Logger.getLogger(FireBaseConnection.class.getName());

    @Autowired
    private MassagingFireBaseContext context;

    @Override
    public List<String> send(List<String> body) {
        logger.info("Sanding ");
        List<String> responses = new LinkedList<>();

        body.forEach((k) -> {
            try {
                responses.add(postByURLJsonBody(context.getConnectionUrl(), context.getApiKey(), k));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });

        return responses;
    }


}
