package com.lbycpd2.todoexp.restful.userpackage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
}
