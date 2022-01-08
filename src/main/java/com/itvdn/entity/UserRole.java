package com.itvdn.entity;

import javax.persistence.*;

@Entity(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_description")
    private String roleDescription;

    public UserRole() {
    }

    public UserRole(String roleName, String roleDescription) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                '}';
    }
}
