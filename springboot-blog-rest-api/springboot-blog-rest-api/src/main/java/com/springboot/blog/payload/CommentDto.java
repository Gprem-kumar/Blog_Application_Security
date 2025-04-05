package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto
{
    private long id;
    // name should not be empty or null
    @NotEmpty(message = "Name should not be null or empty")
    private String name;
    // Email should not be null or empty
    // email validation
    @NotEmpty(message = "Email should not br null or empty")
    @Email
    private String Email;
    // Comment Body should not be null or empty
    // Comment Body must be minimum 10 characters
    @NotEmpty(message = "Comment Body should not empty or null")
    @Size(min = 10, message = "Comment Body must be minimum 10 characters")
    private String body;
}
