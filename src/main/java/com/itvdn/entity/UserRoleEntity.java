package com.itvdn.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_role", schema = "support-service")
public class UserRoleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "role_name")
    private String roleName;

    @Basic
    @Column(name = "role_description")
    private String roleDescription;

    @OneToMany(mappedBy = "userRole", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private List<UserEntity> users = new ArrayList<>();

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<PermissionEntity> permissionEntityList = new ArrayList<>();

    public UserRoleEntity() {
    }

    public UserRoleEntity(String roleName, String roleDescription) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<PermissionEntity> getPermissionEntityList() {
        return permissionEntityList;
    }

    public void setPermissionEntityList(List<PermissionEntity> permissionEntityList) {
        this.permissionEntityList = permissionEntityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleEntity that = (UserRoleEntity) o;
        return id == that.id && Objects.equals(roleName, that.roleName) && Objects.equals(roleDescription, that.roleDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, roleDescription);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                '}';
    }
}
