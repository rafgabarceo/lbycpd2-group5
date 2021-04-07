package com.lbycpd2.todoexp.restful;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class ChildTask extends Task {

    @Id @GeneratedValue @Column(name = "child_id") Long childId;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ParentTask parenttask;

}
