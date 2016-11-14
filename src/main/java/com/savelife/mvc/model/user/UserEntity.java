package com.savelife.mvc.model.user;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by anton on 16.08.16.
 */
@Entity
@Table(name = "user", schema = "savelife")
@Data public class UserEntity {

    @Id
    @Column(name = "idUser")
    private long idUser;

    @Column(name = "token")
    private String token;

/*authorization fields*/
    @Column(name = "email")
    private String email;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "enable")
    private boolean enable;
/*authorization fields*/

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
}