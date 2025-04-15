package com.example.sb_neo4j.service;


import com.example.sb_neo4j.model.User;
import com.example.sb_neo4j.repository.UserRepository;
import com.example.sb_neo4j.request.CreateUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public List<User> getAllUser(){ return userRepository.findAll(); }

    public User createUser(CreateUserRequest createUserRequest){
        User user = new User();
        user.setName(createUserRequest.getName());
        user.setRole(createUserRequest.getRole());

        userRepository.save(user);

        return user;
    }

}
