package com.itvdn.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_service", schema = "support-service")
@IdClass(UserServiceEntityPK.class)
public class UserServiceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "service_id")
    private int serviceId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ServiceEntity serviceByServiceId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private UserEntity userByUserId;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserServiceEntity that = (UserServiceEntity) o;
        return serviceId == that.serviceId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, userId);
    }

    public ServiceEntity getServiceByServiceId() {
        return serviceByServiceId;
    }

    public void setServiceByServiceId(ServiceEntity serviceByServiceId) {
        this.serviceByServiceId = serviceByServiceId;
    }

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
