package com.lbycpd2.todoexp.restful.user.tasks.child;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<ChildTask, Long> {
}
