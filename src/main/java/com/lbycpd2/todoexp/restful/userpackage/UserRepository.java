package com.lbycpd2.todoexp.restful.userpackage;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {
}
