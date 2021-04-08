package com.lbycpd2.todoexp.restful.taskpackage;

import javax.persistence.*;

@Entity
public class ChildTask extends Task {

    @Id @GeneratedValue @Column(name = "child_id") Long childId;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ParentTask parenttask;

}
