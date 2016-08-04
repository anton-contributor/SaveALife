package com.savelife.mvc.service.maps.bing;

import com.savelife.mvc.model.NodeEntity;
import com.savelife.mvc.service.exceptions.IncorrectRequstCoordinatesException;
import com.savelife.mvc.service.exceptions.RoutingServerNotRespondingException;
import com.savelife.mvc.service.maps.Routing;
import com.savelife.mvc.service.maps.MapService;

import java.io.IOException;
import java.net.MalformedURLException;

public class BingMapService implements MapService {

    public static final String BING_URL = "http://dev.virtualearth.net/REST/V1/Routes/Driving?";
    public static final String BING_APIKEY = "An_UXu7iDDMagpqG2K4PnV8plW72VDdzV0lFY6JfusfLsFirSlDwmqwdhJTcZO7J";

    @Override
    public Routing getRouting(NodeEntity start, NodeEntity end) throws IncorrectRequstCoordinatesException, RoutingServerNotRespondingException {
        String URL = BING_URL +
                "wp.0=" + start.getLatitude() + "," + start.getLongitude() + "&" +
                "wp.1=" + end.getLatitude() + "," + end.getLongitude() + "&" +
                "ra=routePath" + "&" +"optimize=timeWithTraffic" + "&" + "maxSolns=3" + "&" +
                "key=" + BING_APIKEY;

        try {
            String response = doGetRequest(URL);
            return new BingRouting(response);
        } catch (MalformedURLException exc){
            throw new IncorrectRequstCoordinatesException("Incorrect coordinates");
        } catch (IOException exc){
            throw new RoutingServerNotRespondingException("Server not responding");
        }

    }

}
