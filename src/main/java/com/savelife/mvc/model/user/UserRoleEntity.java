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
    private int id_user_role;

    @Column(name = "userRole")
    private String user_role;

    @OneToMany(mappedBy = "user_role")
    private Set<UserEntity> userEntities = new HashSet<UserEntity>();

    public int getId_user_role() {
        return id_user_role;
    }

    public void setId_user_role(int id_user_role) {
        this.id_user_role = id_user_role;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
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
        sb.append("id_user_role=").append(id_user_role);
        sb.append(", user_role='").append(user_role).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
