package com.example.sb_neo4j.services;


import com.example.sb_neo4j.models.User;
import com.example.sb_neo4j.repositories.UserRepository;
import com.example.sb_neo4j.requests.CreateUserRequest;
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
