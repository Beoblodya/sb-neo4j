package com.example.sb_neo4j.controllers;


import com.example.sb_neo4j.models.Person;
import com.example.sb_neo4j.models.User;
import com.example.sb_neo4j.objects.UserDTO;
import com.example.sb_neo4j.requests.CreatePersonRequestOrDTO;
import com.example.sb_neo4j.requests.CreateUserRequest;
import com.example.sb_neo4j.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/User")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody CreateUserRequest request) {
        User user = userService.createUser(request);

        UserDTO responseUser = new UserDTO(user.getName(), user.getRole());

        return new ResponseEntity<>(responseUser, HttpStatus.CREATED);
    }
}