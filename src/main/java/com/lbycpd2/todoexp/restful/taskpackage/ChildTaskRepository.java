package com.lbycpd2.todoexp.restful.taskpackage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildTaskRepository extends JpaRepository<ChildTask, Long> {
}
