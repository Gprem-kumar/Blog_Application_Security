package com.springboot.blog.service.impl;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.respository.PostRespository;
import com.springboot.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService
{
    private PostRespository postRespository;
    private ModelMapper mapper;    // inject Model Mapper bean into the Service impl inject using constructor based dependency injection
   //Chip_101--Dependency Injection via Constructor is performed here
    public PostServiceImpl(PostRespository postRespository,ModelMapper mapper)
    {
    this.postRespository=postRespository;
    this.mapper=mapper;
    }
    // method implementation which is declared in the PostService interface
    @Override
   public PostDto createDto(PostDto postDto) //method should return PostDto object
    {
        // convert dto to the entity
        Post post=mapToEntity(postDto);
        Post newPost=postRespository.save(post);
        
       // Convert entity to dto
       PostDto postResponse=mapToDTO(post);
       return postResponse;   // Method returning PostDto object
    }
    @Override
    public List<PostDto> getAllPosts()
    {
       List<Post> posts= postRespository.findAll();
    return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }
    @Override
    public PostDto getPostByID(long id)
    {
       Post post=postRespository.findById(id).orElseThrow(()-> new ResourceNotFoundException("id" ,"Post", id));
       return mapToDTO(post);
    }
    @Override
    public PostDto updatePostById(PostDto postDto, long id)
    {
        // get post by id from the database
        Post post=postRespository.findById(id).orElseThrow(()-> new ResourceNotFoundException("id" ,"Post", id));
      post.setTitle(postDto.getTitle());
      post.setContent(postDto.getContent());
      post.setDescription(postDto.getDescription());
      Post updatePost =postRespository.save(post);
      return mapToDTO(updatePost);
    }
    @Override
    public void deletePostById(long id)
    {
        Post post=postRespository.findById(id).orElseThrow(()-> new ResourceNotFoundException("id" ,"Post", id));
        postRespository.delete(post);
    }
    // converts the dto to the entity
    private Post mapToEntity(PostDto postDto)
    {
//        Post post =new Post();
//        post.setId(postDto.getId());
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(post.getContent());
        Post post=mapper.map(postDto,Post.class);
        return post;
    }
    // Converts Entity to DTO
    private PostDto mapToDTO(Post post)
    {
        //mapping use model mapper library for mapping the object from to another
        PostDto postDto=mapper.map(post, PostDto.class);
//        PostDto postDto=new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return postDto;
    }
}
