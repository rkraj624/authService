package com.security.auth.service;

import com.security.auth.dto.LoginDTO;
import com.security.auth.dto.LoginResponse;
import com.security.auth.model.User;
import com.security.auth.security.util.JWTUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    public LoginService(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    public LoginResponse login(LoginDTO loginDTO){
        User user = userService.findByEmail(loginDTO.getEmail());
        if(user == null){
            throw new UsernameNotFoundException("User doesn't exist");
        }
        boolean matches = bCryptPasswordEncoder.matches(loginDTO.getPassword(), user.getPassword());
        if(!matches){
            throw new RuntimeException("incorrect creds");
        }
        String token = JWTUtil.generateToken(loginDTO.getEmail(), 100);
        return LoginResponse.builder().token(token).user(user).build();
    }
}
