package com.itvdn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String serviceName;
    private boolean isActive;
    private double serviceMonthPrice;

    public Service() {
    }

    public Service(String serviceName, boolean isActive, double serviceMonthPrice) {
        this.serviceName = serviceName;
        this.isActive = isActive;
        this.serviceMonthPrice = serviceMonthPrice;
    }

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public double getServiceMonthPrice() {
        return serviceMonthPrice;
    }

    public void setServiceMonthPrice(double serviceMonthPrice) {
        this.serviceMonthPrice = serviceMonthPrice;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", isActive=" + isActive +
                ", serviceMonthPrice=" + serviceMonthPrice +
                '}';
    }
}
