package com.lbycpd2.todoexp.restful.user;

import com.lbycpd2.todoexp.UUIDStringGenerator;
import com.lbycpd2.todoexp.restful.user.tasks.parent.ParentTask;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "client_user")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class User implements UserDetails{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id")
    private String user_id;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String username;
    private UserRole userRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ParentTask> parentTaskList = new ArrayList<>();

    private Double experience = 0.00;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
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
