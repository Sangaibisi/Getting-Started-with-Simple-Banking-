package com.emrullah.aws.dao;

import com.emrullah.aws.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountDAO extends JpaRepository<Account, Integer> {

    // Our thanks to Spring Framework :)
    // We do not need to write any CRUD operations code.
    // Spring Framework will handle this for us.
}
