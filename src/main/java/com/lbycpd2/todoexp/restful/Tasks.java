/*
*
* This implements a LinkedList for fast insert / delete.
*
* This is a collection of parent tasks.
* */
package com.lbycpd2.todoexp.restful;


import javax.persistence.*;
import java.util.*;

@Entity
public class Tasks {
    @Id
    @GeneratedValue
    Long taskId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany(mappedBy = "tasks", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ParentTask> parentTasks;

    public Tasks() {

    }


}
