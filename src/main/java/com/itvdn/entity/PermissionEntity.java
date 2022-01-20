package com.itvdn.entity;

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
    @Basic
    @Column(name = "role")
    private String role;

    public PermissionEntity() {
    }

    public PermissionEntity(String command, String role) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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
                ", role='" + role + '\'' +
                '}';
    }
}
