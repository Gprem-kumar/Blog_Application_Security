package com.springboot.blog.controller;
import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController     //@RestController is a combination of @ResponseBody and @Controller annotation
@RequestMapping("/api/posts") //
public class PostController
{
    // injecting the interface
   private PostService postService;
   // if we use interface then its loosing coupling
    public PostController(PostService postService)
    {
        this.postService=postService;
    }
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto)
    {
        // call the PostService Method
        PostDto createdPost=postService.createDto(postDto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
    // get ll posts rest api
    @GetMapping
    public List<PostDto> getAllPosts()
    {
        return postService.getAllPosts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name= "id") long id)
    {
        // Call the service layer to fetch the post by its ID
        PostDto post = postService.getPostByID(id);
        // Check if the post exists. If the post is found, return it in the response body.
        // If not, we could handle it with a different response status, e.g., NOT_FOUND.
        if (post != null)
        {
            return ResponseEntity.ok(post); // Return HTTP status 200 (OK) with the post data
        }
        else
        {
            return ResponseEntity.notFound().build(); // Return HTTP status 404 (Not Found) if the post doesn't exist
        }
    }
    // update post by id
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody  PostDto postDto,@PathVariable(name="id") long id)
    {
       PostDto postResponse = postService.updatePostById(postDto,id);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }
    // delete post rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable long id)
    {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post is delete Successfully.",HttpStatus.OK);
    }
}
