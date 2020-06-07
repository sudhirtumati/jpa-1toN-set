package com.sample.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @EqualsAndHashCode.Include
    private String id;

    private String name;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;

    public void addComment(Comment comment) {
        if (comments == null) {
            comments = new HashSet<>();
        }
        comment.setPost(this);
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        if(comments == null || comments.isEmpty()) {
            return;
        }
        comments.remove(comment);
        comment.setPost(null);
    }
}
