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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (idUser != that.idUser) return false;
        if (userRoleIdUserRole != that.userRoleIdUserRole) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idUser ^ (idUser >>> 32));
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + userRoleIdUserRole;
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "idUser=" + idUser +
                ", token='" + token + '\'' +
                ", userRoleIdUserRole=" + userRoleIdUserRole +
                '}';
    }
}
