package com.springboot.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User
{
    @Id
    private int id;
    private String userName;
    private String password;
}
