package com.springboot.blog.service;
import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import java.util.List;

public interface PostService
{
    // method of PostDto type
    PostDto createDto(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostByID(long id );

    PostDto updatePostById(PostDto postDto, long id);

    void deletePostById(long id);
}
