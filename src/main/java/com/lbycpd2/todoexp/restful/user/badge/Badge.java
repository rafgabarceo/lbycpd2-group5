package com.lbycpd2.todoexp.restful.user.badge;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lbycpd2.todoexp.restful.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Badge {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Setter(AccessLevel.NONE)
    @Column(name = "badge_id")
    private String badge_id;

    @Getter(value = AccessLevel.NONE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private String title;
    private String description;
    private Double expRequirement;
}
