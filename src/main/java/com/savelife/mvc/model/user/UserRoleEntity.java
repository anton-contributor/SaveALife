package com.savelife.mvc.model.user;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by anton on 16.08.16.
 */
@Entity
@Table(name = "user_role", schema = "savelife")
public class UserRoleEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "userRole")
    private String userRole;

    @OneToMany(mappedBy = "userRole")
    private Set<UserEntity> userEntities = new HashSet<UserEntity>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Set<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserRoleEntity{");
        sb.append("id_user_role=").append(id);
        sb.append(", user_role='").append(userRole).append('\'');
        sb.append('}');
        return sb.toString();
    }
}