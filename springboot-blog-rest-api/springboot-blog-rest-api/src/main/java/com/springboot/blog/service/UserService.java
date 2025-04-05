package com.springboot.blog.service;


import com.springboot.blog.entity.User;
import com.springboot.blog.respository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService
{
    @Autowired
   private UserRepo userRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User saveUser(User user)
    {
      user.setPassword(encoder.encode(user.getPassword()));
      System.out.println(user.getPassword());
     return userRepo.save(user);
    }
}
