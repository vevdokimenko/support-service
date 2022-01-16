package com.itvdn.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "incident", schema = "support-service")
public class IncidentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "service_name")
    private String serviceName;
    @Basic
    @Column(name = "is_active")
    private byte isActive;
    @Basic
    @Column(name = "problem_description")
    private String problemDescription;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private UserEntity userByUserId;

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

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
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
        IncidentEntity that = (IncidentEntity) o;
        return id == that.id && isActive == that.isActive && userId == that.userId && Objects.equals(serviceName, that.serviceName) && Objects.equals(problemDescription, that.problemDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceName, isActive, problemDescription, userId);
    }

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
