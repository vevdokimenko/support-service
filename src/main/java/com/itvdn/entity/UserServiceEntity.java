package com.itvdn.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_service", schema = "support-service")
public class UserServiceEntity implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public UserServiceEntity() {
    }

    public UserServiceEntity(ServiceEntity service, UserEntity user) {
        this.service = service;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserServiceEntity that = (UserServiceEntity) o;
        return service.equals(that.service) && user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(service, user);
    }

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserServiceEntity{" +
                "service=" + service.getServiceName() +
                ", user=" + user.getUserName() +
                '}';
    }
}
