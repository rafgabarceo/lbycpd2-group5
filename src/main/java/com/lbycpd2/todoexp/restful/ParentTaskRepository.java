package com.lbycpd2.todoexp.restful;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentTaskRepository extends JpaRepository<ParentTask, Long> {
}
