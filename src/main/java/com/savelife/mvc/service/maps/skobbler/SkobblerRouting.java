package com.savelife.mvc.service.maps.skobbler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.savelife.mvc.model.NodeEntity;
import com.savelife.mvc.model.RouteEntity;
import com.savelife.mvc.service.maps.Routing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SkobblerRouting implements Routing {

    private String responseSkobblerAPI;

    public SkobblerRouting(String responseSkobblerAPI){
        this.responseSkobblerAPI = responseSkobblerAPI;
    }

    public RouteEntity getRouteEntity(){

        String routingJSON = responseSkobblerAPI;
        return findDirectionByName(routingJSON, "route");

    }

    public List<RouteEntity> getAllRouteEntity(){
        String routingJSON = responseSkobblerAPI;
        List<RouteEntity> directions = new ArrayList<RouteEntity>();
        directions.add(findDirectionByName(routingJSON, "route"));
        RouteEntity newDirection = findDirectionByName(routingJSON, "alternate" + 1);
        for (int i = 2; newDirection != null; i++) {
            directions.add(newDirection);
            newDirection = findDirectionByName(routingJSON, "alternate" + i);
        }
        return directions;
    }

    private RouteEntity findDirectionByName(String json, String fieldName){

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode directionNode = mapper.readTree(json).findValue(fieldName);
            List<JsonNode> nodesJSON = directionNode.findValue("advisor").findValues("coordinates");
            List<NodeEntity> wayNodes = new ArrayList<NodeEntity>();
            for (JsonNode node : nodesJSON) {
                wayNodes.add(new NodeEntity(node.findValue("y").asDouble(),
                        node.findValue("x").asDouble()));
            }

            RouteEntity direction = new RouteEntity();
            direction.setWayNodes(wayNodes);
            direction.setDistanceInMeter(directionNode.findValue("routelength").asInt());
            direction.setTime(directionNode.findValue("duration").asInt());
            return direction;
        } catch (NullPointerException | IOException exc){
            return null;
        }
    }
}
