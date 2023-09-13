package com.engsoft2.mysqlbackendapispringboot.repository;

import com.engsoft2.mysqlbackendapispringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByName(String name);
}
