package com.savelife.mvc.model.user;

import javax.persistence.*;

/**
 * Created by anton on 16.08.16.
 */
@Entity
@Table(name = "user", schema = "savelife")
public class UserEntity {

    @Id
    @Column(name = "idUser")
    private long idUser;

    @Column(name = "token")
    private String token;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

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


    public UserEntity() {
    }

    public UserEntity(String token, boolean enable, Double currentLatitude, Double currentLongitude, Double destinationLatitude, Double destinationLongitude, UserRoleEntity userRole) {
        this.token = token;
        this.enable = enable;
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
        this.userRole = userRole;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (idUser != that.idUser) return false;
        if (enable != that.enable) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (currentLatitude != null ? !currentLatitude.equals(that.currentLatitude) : that.currentLatitude != null)
            return false;
        if (currentLongitude != null ? !currentLongitude.equals(that.currentLongitude) : that.currentLongitude != null)
            return false;
        if (destinationLatitude != null ? !destinationLatitude.equals(that.destinationLatitude) : that.destinationLatitude != null)
            return false;
        if (destinationLongitude != null ? !destinationLongitude.equals(that.destinationLongitude) : that.destinationLongitude != null)
            return false;
        return userRole != null ? userRole.equals(that.userRole) : that.userRole == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (idUser ^ (idUser >>> 32));
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (enable ? 1 : 0);
        result = 31 * result + (currentLatitude != null ? currentLatitude.hashCode() : 0);
        result = 31 * result + (currentLongitude != null ? currentLongitude.hashCode() : 0);
        result = 31 * result + (destinationLatitude != null ? destinationLatitude.hashCode() : 0);
        result = 31 * result + (destinationLongitude != null ? destinationLongitude.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "idUser=" + idUser +
                ", token='" + token + '\'' +
                ", email=" + email +
                ", enable=" + enable +
                ", currentLatitude=" + currentLatitude +
                ", currentLongitude=" + currentLongitude +
                ", destinationLatitude=" + destinationLatitude +
                ", destinationLongitude=" + destinationLongitude +
                ", userRole=" + userRole +
                '}';
    }
}