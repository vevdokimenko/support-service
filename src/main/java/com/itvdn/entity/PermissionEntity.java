package com.itvdn.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "permission", schema = "support-service")
public class PermissionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "command")
    private String command;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "role_id")
    private UserRoleEntity role;

    public PermissionEntity() {
    }

    public PermissionEntity(String command, UserRoleEntity role) {
        this.command = command;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public UserRoleEntity getRole() {
        return role;
    }

    public void setRole(UserRoleEntity role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionEntity that = (PermissionEntity) o;
        return id == that.id && Objects.equals(command, that.command) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, command, role);
    }

    @Override
    public String toString() {
        return "PermissionEntity{" +
                "id=" + id +
                ", command='" + command + '\'' +
                ", role='" + role.getRoleName() + '\'' +
                '}';
    }
}
