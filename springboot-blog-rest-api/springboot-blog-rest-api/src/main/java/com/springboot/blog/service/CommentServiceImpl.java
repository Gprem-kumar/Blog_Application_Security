package com.springboot.blog.service;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.respository.CommentsRepository;
import com.springboot.blog.respository.PostRespository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService
{
    private CommentsRepository commentsRepository;
    private ModelMapper mapper;
    private PostRespository postRespository;

    public CommentServiceImpl(CommentsRepository commentsRepository, PostRespository postRespository, ModelMapper mapper)
    {
        this.commentsRepository = commentsRepository;
        this.postRespository = postRespository;
        this.mapper=mapper;
    }
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto)
    {
        Comment comment =mapToEntity(commentDto);
        // retrieve post by id
        Post post=postRespository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post" ,"id",postId));
        // set post to the comment entity
        comment.setPost(post);
         Comment newComment =commentsRepository.save(comment);
        return mapToDTO(newComment);
    }
    @Override
    public List<CommentDto> getCommentsByPostId(long postId)
    {
        //retrieve comments by Post Id
        List<Comment> comments=commentsRepository.findByPostId(postId);
         // java 8 stream api
        // convert List of entities to List  of comment dtos
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }
    @Override
    public CommentDto getCommentById(long postId, long commentId)
    {
        Post post=postRespository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post" ,"id",postId));

        // retrieve comment by id
       Comment comment= commentsRepository.findById(commentId).orElseThrow(
               () -> new ResourceNotFoundException("comment" ,"id",commentId));

        // validating
        if(!comment.getPost().getId().equals(post.getId()))
        {
            throw new BlogApiException(HttpStatus.BAD_GATEWAY,"comment does not belong to post");
        }
       return mapToDTO(comment);
    }
    @Override
    public CommentDto updateComment(long postId, long commentId,CommentDto commentRequest)
    {
        Post post=postRespository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("post","id",postId));

        // retrieve comment by id
        Comment comment= commentsRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment" ,"id",commentId));
         // validating
        if(!comment.getPost().getId().equals(post.getId()))
        {
            throw new BlogApiException(HttpStatus.BAD_GATEWAY,"comment does not belong to post");
        }
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getEmail());
        Comment updatedComment=
        commentsRepository.save(comment);

        return mapToDTO(updatedComment);
    }
    @Override
    public void deleteCommentByPostId(long postId, long commentId)
    {
        Post post=postRespository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("post","id",postId));
        // retrieve comment by id
        Comment comment= commentsRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment" ,"id",commentId));
        // validating
        if(!comment.getPost().getId().equals(post.getId()))
        {
            throw new BlogApiException(HttpStatus.BAD_GATEWAY,"comment does not belong to post");
        }
       commentsRepository.delete(comment);
    }
    private CommentDto mapToDTO(Comment comment)
    {
        CommentDto commentDto =mapper.map(comment,CommentDto.class);
       return commentDto;
    }
    private Comment mapToEntity(CommentDto commentDto)
    {
        Comment comment =mapper.map(commentDto,Comment.class);
        return comment;
    }
}
