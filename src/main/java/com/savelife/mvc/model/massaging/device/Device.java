package com.savelife.mvc.model.massaging.device;

import javax.persistence.*;

/**
 * POJO remote device
 */

@Entity
@Table(name = "device")
public class Device {

    /*
    * a cloud massaging personal device token
    * for separate connection to each device
    * */
    @Id
    @GeneratedValue
    @Column(name = "id_device")
    private Long id_device;

    @Column(name = "token")
    private String token;

    public Device(String t) {
        this.token = t;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId_device() {
        return id_device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (id_device != null ? !id_device.equals(device.id_device) : device.id_device != null) return false;
        return token != null ? token.equals(device.token) : device.token == null;

    }

    @Override
    public int hashCode() {
        int result = id_device != null ? id_device.hashCode() : 0;
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id_device=" + id_device +
                ", token='" + token + '\'' +
                '}';
    }
}
