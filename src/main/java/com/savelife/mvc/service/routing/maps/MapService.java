package com.savelife.mvc.service.routing.maps;

import com.savelife.mvc.model.routing.NodeEntity;
import com.savelife.mvc.service.routing.exceptions.IncorrectRequestCoordinatesException;
import com.savelife.mvc.service.routing.exceptions.RoutingServerNotRespondingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public interface MapService {

    Routing getRouting(NodeEntity start, NodeEntity end) throws IncorrectRequestCoordinatesException, RoutingServerNotRespondingException;

    default String doGetRequest(String requestURL) throws IOException {

        StringBuilder response = new StringBuilder();
        URL url = new URL(requestURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null){
            response.append(line);
        }
        reader.close();
        String responseString = response.toString();
        return responseString;

    }
}
