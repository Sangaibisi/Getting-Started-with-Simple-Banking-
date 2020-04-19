package com.emrullah.aws.dao;

import com.emrullah.aws.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDAO extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
}
