package com.example.sb_neo4j.controller;


import com.example.sb_neo4j.model.User;
import com.example.sb_neo4j.dto.UserDTO;
import com.example.sb_neo4j.request.CreateUserRequest;
import com.example.sb_neo4j.service.UserService;
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