/*
*
* This will contain a collection of children tasks
*
* */
package com.lbycpd2.todoexp.restful;

import net.bytebuddy.dynamic.scaffold.MethodGraph;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
public class ParentTask {
    @Id
    @GeneratedValue Long parentId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "taskId", nullable = false)
    private Tasks tasks;

    private String title;
    private String description;
    private boolean status;

    @OneToMany(mappedBy = "parenttask", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ChildTask> childTasks;

    public ParentTask(){}

    public ParentTask(String title, String description, boolean status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAccomplished;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateAccomplished() {
        return dateAccomplished;
    }

    public void setDateAccomplished(Date dateAccomplished) {
        this.dateAccomplished = dateAccomplished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParentTask that = (ParentTask) o;
        return status == that.status && Objects.equals(parentId, that.parentId) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(dateCreated, that.dateCreated) && Objects.equals(dateAccomplished, that.dateAccomplished);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, title, description, status, dateCreated, dateAccomplished);
    }

    @Override
    public String toString() {
        return "ParentTask{" +
                "parentId=" + parentId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                ", dateAccomplished=" + dateAccomplished +
                '}';
    }
}
