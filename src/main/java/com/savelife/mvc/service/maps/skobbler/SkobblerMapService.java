package com.savelife.mvc.service.maps.skobbler;

import com.savelife.mvc.model.NodeEntity;
import com.savelife.mvc.service.exceptions.IncorrectRequstCoordinatesException;
import com.savelife.mvc.service.exceptions.RoutingServerNotRespondingException;
import com.savelife.mvc.service.maps.MapService;
import com.savelife.mvc.service.maps.Routing;

import java.io.IOException;
import java.net.MalformedURLException;

public class SkobblerMapService implements MapService {

    public final static String SKOBBLER_APIKEY = "99067e1f530be546288a0f38710010b9";
    public final static String SKOBBLER_URL = "http://" + SKOBBLER_APIKEY + ".tor.skobbler.net" +
                                              "/tor/RSngx/calcroute/json/18_0/en/" + SKOBBLER_APIKEY;

    @Override
    public Routing getRouting(NodeEntity start, NodeEntity end) throws IncorrectRequstCoordinatesException, RoutingServerNotRespondingException {
        String URL = SKOBBLER_URL + "?" + "start=" + start.getLatitude() + "," + start.getLongitude() + "&" +
                "dest=" + end.getLatitude() + "," + end.getLongitude() + "&" +
                "profile=carFastest&alternates=yes&advice=yes";

        try {
            String responseSkobblerAPI = doGetRequest(URL);
            return new SkobblerRouting(responseSkobblerAPI);
        } catch (MalformedURLException exc){
            throw new IncorrectRequstCoordinatesException("Incorrect coordinates");
        } catch (IOException exc){
            throw new RoutingServerNotRespondingException("Server not responding");
        }


    }

}
