package com.springboot.blog.respository;
import com.springboot.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment,Long>
{
     // Custom Query Method
    List<Comment> findByPostId(long id);
}
