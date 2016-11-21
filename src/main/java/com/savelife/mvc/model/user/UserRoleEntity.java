package com.savelife.mvc.model.user;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by anton on 16.08.16.
 */
@Entity
@Table(name = "user_role", schema = "savelife")
@Data
@ToString(exclude = "userEntities")
public class UserRoleEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "user_role")
    private String userRole;

    @OneToMany(mappedBy = "userRole")
    private Set<UserEntity> userEntities = new HashSet<UserEntity>();

}
