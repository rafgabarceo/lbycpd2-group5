package com.lbycpd2.todoexp.restful.user.tasks.parent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentTaskRepository extends JpaRepository<ParentTask, Long> {
}
