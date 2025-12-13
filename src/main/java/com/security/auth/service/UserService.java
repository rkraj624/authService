package com.security.auth.service;

import com.security.auth.dto.UserDTO;
import com.security.auth.model.User;
import com.security.auth.repository.CommonJPARepository;
import com.security.auth.repository.UserRepository;
import tools.jackson.databind.ObjectMapper;

public class UserService extends AbstractCDMService<User>{

    private final UserRepository userRepository;
    ObjectMapper objectMapper;

    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }
    private User createUser(UserDTO userDTO){
        User user = objectMapper.convertValue(userDTO, User.class);
        return super.save(user);
    }
}
