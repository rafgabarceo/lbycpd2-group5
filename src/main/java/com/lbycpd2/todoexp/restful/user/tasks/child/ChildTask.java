package com.lbycpd2.todoexp.restful.user.tasks.child;

import com.lbycpd2.todoexp.restful.user.tasks.Task;
import com.lbycpd2.todoexp.restful.user.tasks.parent.ParentTask;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "child_task")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChildTask extends Task {

    @Id @GeneratedValue @Column(name = "child_id") Long childId;

    @ManyToOne @JoinColumn(name = "parent_id")
    private ParentTask parenttask;
}
