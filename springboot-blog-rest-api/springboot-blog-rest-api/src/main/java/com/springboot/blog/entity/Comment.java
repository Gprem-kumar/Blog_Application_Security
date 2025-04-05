package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Comments_Table")
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String Email;
    private String body;
    @ManyToOne(fetch = FetchType.LAZY) // fetch.type.lazy tells the hibernate to fetch the particular entity
    @JoinColumn
            (name="post_id", nullable = false) // for specifying the foreign key and for adding the new column
    private Post post;
}

