package com.lbycpd2.todoexp.restful.user;

import com.lbycpd2.todoexp.restful.ParentTask;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "clientUser")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long user_id;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private UserRole userRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ParentTask> parentTaskList;

    private Double experience = 0.00;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ParentTask> getParentTaskList() {
        return parentTaskList;
    }

    public void setParentTaskList(List<ParentTask> parentTaskList) {
        this.parentTaskList = parentTaskList;
    }

    public User(String firstName, String lastName, String email, String password, UserRole userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
        this.experience = 0.00;
    }

    // as suggested from https://stackoverflow.com/questions/53647672/how-to-save-parent-and-child-in-one-shot-jpa-hibernate
    public void addParentTask(ParentTask parentTask){
        parentTask.setUser(this);
        this.parentTaskList.add(parentTask);
    }

    public void deleteParentTask(ParentTask parentTask){
        this.parentTaskList.remove(parentTask);
    }

    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
