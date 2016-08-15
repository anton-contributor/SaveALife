package com.savelife.mvc.model.routing;


import javax.persistence.*;

@Entity
@Table(name = "node")
public class NodeEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_route")
    private Long id_node;


    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    public NodeEntity() {
    }

    public NodeEntity(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId_node() {
        return id_node;
    }

    public void setId_node(Long id_node) {
        this.id_node = id_node;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeEntity that = (NodeEntity) o;

        if (Double.compare(that.latitude, latitude) != 0) return false;
        return Double.compare(that.longitude, longitude) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "NodeEntity{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
