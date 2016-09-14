package com.savelife.mvc.service.sender;

import com.savelife.mvc.apis.messaging.MassageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by anton on 10.08.16.
 */
@Service
public class SenderServiceImpl implements SenderService<String> {

    @Autowired
    private MassageSender connection;


    @Override
    @SuppressWarnings("unchecked")
    public List<String> send(List<String> body) throws UnsupportedEncodingException {
        return connection.send(body);
    }
}
