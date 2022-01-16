package com.itvdn.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "service", schema = "support-service")
public class ServiceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "service_name")
    private String serviceName;
    @Basic
    @Column(name = "is_active")
    private boolean isActive;
    @Basic
    @Column(name = "service_month_price")
    private double serviceMonthPrice;
    @Basic
    @Column(name = "customer_id")
    private int customerId;
    @OneToMany(mappedBy = "serviceByServiceId")
    private Collection<UserServiceEntity> userServicesById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public double getServiceMonthPrice() {
        return serviceMonthPrice;
    }

    public void setServiceMonthPrice(double serviceMonthPrice) {
        this.serviceMonthPrice = serviceMonthPrice;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceEntity that = (ServiceEntity) o;
        return id == that.id && isActive == that.isActive && Double.compare(that.serviceMonthPrice, serviceMonthPrice) == 0 && customerId == that.customerId && Objects.equals(serviceName, that.serviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceName, isActive, serviceMonthPrice, customerId);
    }

    public Collection<UserServiceEntity> getUserServicesById() {
        return userServicesById;
    }

    public void setUserServicesById(Collection<UserServiceEntity> userServicesById) {
        this.userServicesById = userServicesById;
    }

    @Override
    public String toString() {
        return "ServiceEntity{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", isActive=" + isActive +
                ", serviceMonthPrice=" + serviceMonthPrice +
                ", customerId=" + customerId +
                '}';
    }
}
