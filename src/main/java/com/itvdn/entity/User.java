package com.itvdn.entity;

import javax.persistence.*;

@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_role_id", foreignKey = @ForeignKey(name = "user_role_id_fk"))
    private UserRole userRoleId;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profileId;

    public User() {
    }

    public User(String userName, String password, UserRole userRoleId, Profile profileId) {
        this.userName = userName;
        this.password = password;
        this.userRoleId = userRoleId;
        this.profileId = profileId;
    }

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

    public UserRole getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(UserRole userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Profile getProfileId() {
        return profileId;
    }

    public void setProfileId(Profile profileId) {
        this.profileId = profileId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userRoleId=" + userRoleId +
                ", profileId=" + profileId +
                '}';
    }
}
