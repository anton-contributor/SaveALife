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

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @ManyToOne
    @JoinColumn(name = "user_role_id_user_role")
    private UserRoleEntity user_role;


    public UserEntity() {
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public UserRoleEntity getUser_role() {
        return user_role;
    }

    public void setUser_role(UserRoleEntity user_role) {
        this.user_role = user_role;
    }


}