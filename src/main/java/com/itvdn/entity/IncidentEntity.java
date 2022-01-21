package com.itvdn.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "incident", schema = "support-service")
public class IncidentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "service_name")
    private String serviceName;

    @Basic
    @Column(name = "is_active")
    private boolean isActive;

    @Basic
    @Column(name = "problem_description")
    private String problemDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public IncidentEntity() {
    }

    public IncidentEntity(long id, String serviceName, boolean isActive, String problemDescription, UserEntity user) {
        this.id = id;
        this.serviceName = serviceName;
        this.isActive = isActive;
        this.problemDescription = problemDescription;
        this.user = user;
    }

    public IncidentEntity(String serviceName, boolean isActive, String problemDescription, UserEntity user) {
        this.serviceName = serviceName;
        this.isActive = isActive;
        this.problemDescription = problemDescription;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncidentEntity that = (IncidentEntity) o;
        return id == that.id && isActive == that.isActive && Objects.equals(serviceName, that.serviceName) && Objects.equals(problemDescription, that.problemDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceName, isActive, problemDescription);
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", isActive=" + isActive +
                ", problemDescription='" + problemDescription + '\'' +
                ", user=" + user.getId() +
                '}';
    }
}
