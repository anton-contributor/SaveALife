package com.savelife.mvc.model.user;

import javax.persistence.*;

/**
 * Created by anton on 16.08.16.
 */
@Entity
@Table(name = "user", schema = "savelife")
public class UserEntity {

    @Id
    @Column(name = "id_user")
    private long idUser;

    @Column(name = "token")
    private String token;

    @Column(name = "enable")
    private boolean enable;

    @Column(name = "currentLatitude")
    private String currentLatitude;

    @Column(name = "currentLongitude")
    private String currentLongitude;

    @Column(name = "destinationLatitude")
    private String destinationLatitude;

    @Column(name = "destinationLongitude")
    private String destinationLongitude;

    @ManyToOne
    @JoinColumn(name = "user_role_id_user_role")
    private UserRoleEntity user_role;


    public UserEntity() {
    }

    public String getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(String destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public String getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(String destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

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

    public String getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(String currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public String getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(String currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public UserRoleEntity getUser_role() {
        return user_role;
    }

    public void setUser_role(UserRoleEntity user_role) {
        this.user_role = user_role;
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
        sb.append(", user_role=").append(user_role);
        sb.append('}');
        return sb.toString();
    }
}