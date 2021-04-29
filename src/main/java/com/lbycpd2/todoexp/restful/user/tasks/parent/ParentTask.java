/*
*
* This will contain a collection of children tasks
*
* */
package com.lbycpd2.todoexp.restful.user.tasks.parent;

import com.lbycpd2.todoexp.UUIDStringGenerator;
import com.lbycpd2.todoexp.restful.user.tasks.Task;
import com.lbycpd2.todoexp.restful.user.tasks.child.ChildTask;
import com.lbycpd2.todoexp.restful.user.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parent_task")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParentTask extends Task {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "parent_id") String parentId;

    @Getter(value = AccessLevel.NONE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "parenttask", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ChildTask> childTasks;

    public String getUserId(){
        return user.getUser_id();
    }
}
