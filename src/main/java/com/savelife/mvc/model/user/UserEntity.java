package com.savelife.mvc.model.user;

import javax.persistence.*;

/**
 * Created by anton on 16.08.16.
 */
@Entity
@Table(name = "user", schema = "savelife")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idUser")
    private long idUser;

    @Column(name = "token")
    private String token;

    @Column(name = "enable")
    private boolean enable;

    @Column(name = "currentLatitude")
    private Double currentLatitude;

    @Column(name = "currentLongitude")
    private Double currentLongitude;

    @Column(name = "destinationLatitude")
    private Double destinationLatitude;

    @Column(name = "destinationLongitude")
    private Double destinationLongitude;

    @ManyToOne
    @JoinColumn(name = "userRoleID")
    private UserRoleEntity userRole;


    public UserEntity() {}

    public UserEntity(String token) {
        this.token = token;
    }


    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }


    public Double getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(Double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }


    public Double getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(Double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }


    public Double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(Double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }


    public Double getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(Double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }


    public UserRoleEntity getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEntity userRole) {
        this.userRole = userRole;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserEntity{");
        sb.append("idUser=").append(idUser);
        sb.append(", token='").append(token).append('\'');
        sb.append(", enable=").append(enable);
        sb.append(", currentLatitude='").append(currentLatitude).append('\'');
        sb.append(", currentLongitude='").append(currentLongitude).append('\'');
        sb.append(", destinationLatitude='").append(destinationLatitude).append('\'');
        sb.append(", destinationLongitude='").append(destinationLongitude).append('\'');
        sb.append(", user_role=").append(userRole);
        sb.append('}');
        return sb.toString();
    }
}