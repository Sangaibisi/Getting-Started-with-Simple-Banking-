package com.emrullah.aws.model;

import com.emrullah.aws.common.GeneralEnumerationDefinitions;

import javax.persistence.*;

@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Users usersCredentials;

    @Column(name="role")
    private String role;

    public UserRole(){}

    public UserRole(GeneralEnumerationDefinitions.USER_ROLE user_role){
        this.role=user_role.getShortCode();
    }
}
