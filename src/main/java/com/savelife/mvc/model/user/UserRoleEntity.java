package com.savelife.mvc.model.user;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by anton on 16.08.16.
 */
@Entity
@Table(name = "user_role", schema = "savelife")
@Data public class UserRoleEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "userRole")
    private String userRole;

    @OneToMany(mappedBy = "userRole")
    private Set<UserEntity> userEntities = new HashSet<UserEntity>();



    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserRoleEntity{");
        sb.append("id_user_role=").append(id);
        sb.append(", user_role='").append(userRole).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
