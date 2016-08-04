package com.savelife.mvc.service.maps.bing;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.savelife.mvc.model.NodeEntity;
import com.savelife.mvc.model.RouteEntity;
import com.savelife.mvc.service.maps.Routing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BingRouting implements Routing{

    private final String responseBing_API;
    private List<RouteEntity> directions;

    public BingRouting(String responseBing_API){
        this.responseBing_API = responseBing_API;
    }

    public RouteEntity getRouteEntity(){

        String routingJSON = responseBing_API;

        if(directions == null){
            directions = findDirections(routingJSON);
        }

        Comparator<RouteEntity> comparatorByTime = new Comparator<RouteEntity>() {
            @Override
            public int compare(RouteEntity o1, RouteEntity o2) {
                return (int)(o1.getTime() - o2.getTime());
            }
        };


        return directions.stream().min(comparatorByTime).get();
    }

    public List<RouteEntity> getAllRouteEntity(){

        String routingJSON = responseBing_API;

        return (directions == null) ?
                directions = findDirections(routingJSON) :
                directions;
    }

    private List<RouteEntity> findDirections(String json){

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode directionsJsonNodes = mapper.readTree(json).findValue("resources");

            List<RouteEntity> directions = new ArrayList<>();
            for (JsonNode directionJson : directionsJsonNodes) {
                JsonNode nodesJson = directionJson.findValue("routePath").findValue("coordinates");
                List<NodeEntity> way = new ArrayList<>();
                for (JsonNode nodeJson : nodesJson) {
                    String[] ccordinates = nodeJson.toString().split(",");
                    NodeEntity node = new NodeEntity(Double.parseDouble(ccordinates[0].replaceAll("[^0-9&&[^\\.-]]", "")),
                            Double.parseDouble(ccordinates[1].replaceAll("[^0-9&&[^\\.-]]", "")));
                    way.add(node);
                }
                RouteEntity direction = new RouteEntity();

                String distanceStr = directionJson.findValue("travelDistance").toString().replaceAll("\\D", "").toString();
                direction.setDistanceInMeter(Integer.parseInt(distanceStr));
                direction.setTime(Integer.parseInt(directionJson.findValue("travelDurationTraffic").toString()));
                direction.setWayNodes(way);

                directions.add(direction);
            }

            return directions;
        } catch (NullPointerException | IOException exc){
            return null;
        }
    }
}
