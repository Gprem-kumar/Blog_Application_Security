package com.springboot.blog.controller;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentsController
{
     private CommentService commentService;

    public CommentsController(CommentService commentService)
    {
        this.commentService = commentService;
    }
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable(name="postId") long id)
    {
        CommentDto commentDto1 = commentService.createComment(id, commentDto);
        return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getAllComments(@PathVariable(value = "postId") long id)
    {
   return commentService.getCommentsByPostId(id);
    }
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "postId") long postId,@PathVariable(name = "id") long commentId)
    {
   CommentDto commentDto=commentService.getCommentById(postId,commentId);
   return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@Valid
            @PathVariable(value = "postId") long id,@PathVariable(value = "id") long commentId,
                                                    CommentDto commentDto)
    {
      CommentDto updatedComment=commentService.updateComment(id,commentId,commentDto);
      return new ResponseEntity<>(updatedComment,HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentByPostId(@PathVariable(value = "postId") long postId,@PathVariable(value = "id") long commentId)
    {
      commentService.deleteCommentByPostId(postId,commentId);
      return new ResponseEntity<>("The Comment is deleted Successfully",HttpStatus.OK);
    }
}
