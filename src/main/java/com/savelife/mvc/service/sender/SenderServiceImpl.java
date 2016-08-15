package com.savelife.mvc.service.sender;

import com.savelife.mvc.service.massaging.connection.HTTPConnection.MassagingConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * Created by anton on 10.08.16.
 */
@Service
public class SenderServiceImpl implements SenderService<String> {

    @Autowired
    private MassagingConnection connection;


    @Override
    @SuppressWarnings("unchecked")
    public String echo(String body) throws UnsupportedEncodingException {
        return connection.echo(body);
    }
}
