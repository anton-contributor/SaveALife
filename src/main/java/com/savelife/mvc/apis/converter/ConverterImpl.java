package com.savelife.mvc.apis.converter;

import com.google.gson.Gson;
import com.savelife.mvc.model.messaging.server.ServerMessage;
import com.savelife.mvc.model.user.UserEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gleb on 26.11.16.
 */
@Service
public class ConverterImpl implements Converter<UserEntity, String> {

    @Override
    public List<String> convert(List<UserEntity> p, Map map) {
        List<String> converted = new ArrayList<>();

        p.forEach((k) -> {
            ServerMessage m = new ServerMessage();
            m.setTo(k.getToken());
            m.setData(map);
            Gson gson = new Gson();
            converted.add(gson.toJson(m));
        });
        return converted;
    }
}
