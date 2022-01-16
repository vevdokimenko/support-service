package com.itvdn.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "support-service")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "user_role_id")
    private int userRoleId;
    @Basic
    @Column(name = "profile_id")
    private int profileId;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<IncidentEntity> incidentsById;
    @ManyToOne
    @JoinColumn(name = "user_role_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private UserRoleEntity userRoleByUserRoleId;
    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ProfileEntity profileByProfileId;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<UserServiceEntity> userServicesById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && userRoleId == that.userRoleId && profileId == that.profileId && Objects.equals(userName, that.userName) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, userRoleId, profileId);
    }

    public Collection<IncidentEntity> getIncidentsById() {
        return incidentsById;
    }

    public void setIncidentsById(Collection<IncidentEntity> incidentsById) {
        this.incidentsById = incidentsById;
    }

    public UserRoleEntity getUserRoleByUserRoleId() {
        return userRoleByUserRoleId;
    }

    public void setUserRoleByUserRoleId(UserRoleEntity userRoleByUserRoleId) {
        this.userRoleByUserRoleId = userRoleByUserRoleId;
    }

    public ProfileEntity getProfileByProfileId() {
        return profileByProfileId;
    }

    public void setProfileByProfileId(ProfileEntity profileByProfileId) {
        this.profileByProfileId = profileByProfileId;
    }

    public Collection<UserServiceEntity> getUserServicesById() {
        return userServicesById;
    }

    public void setUserServicesById(Collection<UserServiceEntity> userServicesById) {
        this.userServicesById = userServicesById;
    }
}
