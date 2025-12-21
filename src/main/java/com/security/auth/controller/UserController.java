package com.security.auth.controller;

import com.security.auth.dto.UserDTO;
import com.security.auth.model.User;
import com.security.auth.proxy.UserReadProxy;
import com.security.auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    /**
     * Proxy design pattern: delegate user read operations to a proxy layer that can enforce cross-cutting
     * concerns (e.g., role checks) before calling the underlying service.
     */
    private final UserReadProxy userReadProxy;

    public UserController(UserService userService, UserReadProxy userReadProxy) {
        this.userService = userService;
        this.userReadProxy = userReadProxy;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<User>> getUser(){
        List<User> userList = userReadProxy.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
