package com.security.auth.service;

import com.security.auth.dto.UserDTO;
import com.security.auth.model.User;
import com.security.auth.repository.CommonJPARepository;
import com.security.auth.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService extends AbstractCDMService<User>{

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public User createUser(UserDTO userDTO){
        User user = objectMapper.convertValue(userDTO, User.class);
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);
        return super.save(user);
    }
}
