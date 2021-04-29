package com.lbycpd2.todoexp.restful.user.tasks.child;

import com.lbycpd2.todoexp.UUIDStringGenerator;
import com.lbycpd2.todoexp.restful.user.tasks.Task;
import com.lbycpd2.todoexp.restful.user.tasks.parent.ParentTask;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "child_task")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChildTask extends Task {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "child_id") String childId;

    @ManyToOne @JoinColumn(name = "parent_id")
    private ParentTask parenttask;
}
