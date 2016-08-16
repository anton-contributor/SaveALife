package com.savelife.mvc.model.user;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by anton on 16.08.16.
 */
public class UserEntityPK implements Serializable {

    private long idUser;
    private int userRoleIdUserRole;

    @Column(name = "id_user")
    @Id
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Column(name = "user_role_id_user_role")
    @Id
    public int getUserRoleIdUserRole() {
        return userRoleIdUserRole;
    }

    public void setUserRoleIdUserRole(int userRoleIdUserRole) {
        this.userRoleIdUserRole = userRoleIdUserRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntityPK that = (UserEntityPK) o;

        if (idUser != that.idUser) return false;
        if (userRoleIdUserRole != that.userRoleIdUserRole) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idUser ^ (idUser >>> 32));
        result = 31 * result + userRoleIdUserRole;
        return result;
    }
}
