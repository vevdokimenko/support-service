package com.itvdn;

import java.util.Objects;

public class Permission {
    private String query;
    private String role;

    public Permission(String query, String role) {
        this.query = query;
        this.role = role;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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
                "query='" + query + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(query, that.query) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query, role);
    }
}
