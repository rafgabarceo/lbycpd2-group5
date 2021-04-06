package com.lbycpd2.todoexp.restful;

import javax.persistence.*;

@Entity
public class ChildTask extends ParentTask {
    @Id
    @GeneratedValue Long childId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId", nullable = false)
    private ParentTask parentTask;

    public ChildTask(){}

    public ChildTask(Long parentId){
        this.parentId = parentId;
    }
}
