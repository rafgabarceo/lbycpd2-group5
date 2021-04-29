/*
*
* This will contain a collection of children tasks
*
* */
package com.lbycpd2.todoexp.restful.user.tasks.parent;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lbycpd2.todoexp.UUIDStringGenerator;
import com.lbycpd2.todoexp.restful.user.tasks.Task;
import com.lbycpd2.todoexp.restful.user.tasks.child.ChildTask;
import com.lbycpd2.todoexp.restful.user.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "parent_task")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParentTask extends Task {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Setter(AccessLevel.NONE)
    @Column(name = "parent_id") String parentId;

    @Getter(value = AccessLevel.NONE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "parenttask", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ChildTask> childTasks = new LinkedList<>();

    public String getUserId(){
        return user.getUser_id();
    }

    public void addChildTask(ChildTask childTask){
        childTask.setParenttask(this);
        childTasks.add(childTask);
    }

    public ParentTask(String title, String description){
        super(title, description);
    }
}
