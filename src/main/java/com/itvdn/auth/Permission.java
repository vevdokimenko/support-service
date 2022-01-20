package com.itvdn.auth;

import java.util.Objects;

public class Permission {
    private String command;
    private String role;

    public Permission(String command, String role) {
        this.command = command;
        this.role = role;
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
    public String toString() {
        return "Permission{" +
                "command='" + command + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(command, that.command) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, role);
    }
}
