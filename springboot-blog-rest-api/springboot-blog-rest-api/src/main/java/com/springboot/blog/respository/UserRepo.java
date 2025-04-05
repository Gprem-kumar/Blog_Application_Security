package com.springboot.blog.respository;

import com.springboot.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer>
{
    User findByUserName(String username);
}
