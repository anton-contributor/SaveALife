package com.savelife.mvc.model.user;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by anton on 16.08.16.
 */
@Entity
@Table(name = "user", schema = "savelife")
@Data
@ToString(exclude = "userContacts")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private long idUser;

    @Column(name = "token")
    private String token;

/*authorization fields*/
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "enable")
    private boolean enable;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "doctor_specialty")
    private String doctorSpecialty;
/*authorization fields*/

    @Column(name = "current_latitude")
    private Double currentLatitude;

    @Column(name = "current_longitude")
    private Double currentLongitude;

    @Column(name = "destination_latitude")
    private Double destinationLatitude;

    @Column(name = "destination_longitude")
    private Double destinationLongitude;

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private UserRoleEntity userRole;

    @JoinTable(name = "user_contacts",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "contact_id", referencedColumnName = "user_id", nullable = false)})
    @ManyToMany
    private List<UserEntity> userContacts;

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