package com.lbycpd2.todoexp.restful.tasks.parent;

import com.lbycpd2.todoexp.restful.tasks.child.ParentTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentTaskRepository extends JpaRepository<ParentTask, Long> {
}
