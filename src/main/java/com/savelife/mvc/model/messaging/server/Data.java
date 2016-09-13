package com.savelife.mvc.model.messaging.server;

import com.savelife.mvc.model.routing.NodeEntity;

import java.util.List;

/**
 * Nested object data of the @ServerMassage
 * consisting of the different options to be sent as a massageBody body
 */
public class Data {

    /*
    * nested option displaying a massageBody body
    * */
    private String massageBody;

    private List<NodeEntity> path;

    private double latitude;
    private double longitude;

    public Data() {
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

    public String getMassageBody() {
        return massageBody;
    }

    public void setMassageBody(String massageBody) {
        this.massageBody = massageBody;
    }

    public List<NodeEntity> getPath() {
        return path;
    }

    public void setPath(List<NodeEntity> path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data = (Data) o;

        if (Double.compare(data.latitude, latitude) != 0) return false;
        if (Double.compare(data.longitude, longitude) != 0) return false;
        if (massageBody != null ? !massageBody.equals(data.massageBody) : data.massageBody != null) return false;
        return path != null ? path.equals(data.path) : data.path == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = massageBody != null ? massageBody.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Data{" +
                "massageBody='" + massageBody + '\'' +
                ", path=" + path +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
