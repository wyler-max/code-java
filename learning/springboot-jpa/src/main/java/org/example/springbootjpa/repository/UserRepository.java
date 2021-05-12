package org.example.springbootjpa.repository;

import org.example.springbootjpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

}
