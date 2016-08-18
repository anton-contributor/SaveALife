package com.savelife.mvc.model.user;

import javax.persistence.*;

/**
 * Created by anton on 16.08.16.
 */
@Entity
@Table(name = "user", schema = "savelife")
@IdClass(UserEntityPK.class)
public class UserEntity {

    private long idUser;
    private String token;
    private boolean enable;
    private String latitude;
    private String longitude;
    private int userRoleIdUserRole;

    public UserEntity() {
    }

    public UserEntity(String token) {
        this.token = token;
    }

    @Id
    @Column(name = "id_user")
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Id
    @Column(name = "user_role_id_user_role")
    public int getUserRoleIdUserRole() {
        return userRoleIdUserRole;
    }

    public void setUserRoleIdUserRole(int userRoleIdUserRole) {
        this.userRoleIdUserRole = userRoleIdUserRole;
    }

    @Column(name = "enable")
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Column(name = "latitude")
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Column(name = "longitude")
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
