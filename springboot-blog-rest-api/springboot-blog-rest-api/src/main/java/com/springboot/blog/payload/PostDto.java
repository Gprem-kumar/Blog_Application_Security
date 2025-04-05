package com.springboot.blog.payload;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto
{
    private long id;
    // Validations_
    // title should not be null or empty
    // title should have at least 2 characters
    @NotEmpty
   @Size(min = 2, message = "Post Title should have at least 2 Characters")
    private String title;
     // Post Description not should be null or empty
      //  Post Description should have at least 10 characters
    @NotEmpty
    @Size(min = 10,message = "Post Description must have at least 10 Characters")
    private String description;
    // Post content should not be null or empty
    @NotEmpty
    private String content;

    private Set<CommentDto> comments;
}
