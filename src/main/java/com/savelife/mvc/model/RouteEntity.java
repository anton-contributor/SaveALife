package com.savelife.mvc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "route")
public class RouteEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_route")
    private Long id_route;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_node", referencedColumnName = "id_route", nullable = false)
    private List<NodeEntity> wayNodes;

    @Column(name = "distance")
    private int distanceInMeter;
    @Column(name = "time")
    int time;

    public Long getId_route() {
        return id_route;
    }

    public void setId_route(Long id_route) {
        this.id_route = id_route;
    }

    public List<NodeEntity> getWayNodes() {
        return wayNodes;
    }

    public void setWayNodes(List<NodeEntity> wayNodes) {
        this.wayNodes = wayNodes;
    }

    public int getDistanceInMeter() {
        return distanceInMeter;
    }

    public void setDistanceInMeter(int distanceInMeter) {
        this.distanceInMeter = distanceInMeter;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteEntity that = (RouteEntity) o;

        if (distanceInMeter != that.distanceInMeter) return false;
        return time == that.time;

    }

    @Override
    public int hashCode() {
        int result = distanceInMeter;
        result = result + time;
        return result;
    }

    @Override
    public String toString() {
        return "RouteEntity{" +
                "id_route=" + id_route +
                ", wayNodes=" + wayNodes +
                ", distanceInMeter=" + distanceInMeter +
                ", time=" + time +
                '}';
    }
}
