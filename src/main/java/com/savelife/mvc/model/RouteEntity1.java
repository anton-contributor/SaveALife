package com.savelife.mvc.model;

import javax.persistence.*;

/**
 * Created by anton on 27.07.16.
 */
@Entity
@Table(name = "route")
public class RouteEntity1{

    @Id
    @GeneratedValue
    @Column(name = "id_route")
    private Long id_route;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;


    public Long getId_route() {
        return id_route;
    }

    public void setId_route(Long id_route) {
        this.id_route = id_route;
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }


    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteEntity1 that = (RouteEntity1) o;

        if (id_route != null ? !id_route.equals(that.id_route) : that.id_route != null) return false;
        if (origin != null ? !origin.equals(that.origin) : that.origin != null) return false;
        return destination != null ? destination.equals(that.destination) : that.destination == null;

    }

    @Override
    public int hashCode() {
        int result = id_route != null ? id_route.hashCode() : 0;
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RouteEntity{" +
                "id_route=" + id_route +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
