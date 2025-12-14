package com.security.auth.security.jwt;

import com.security.auth.model.User;
import com.security.auth.service.UserService;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JWTUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public JWTUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @NonNull
    public JWTUser loadUserByUsername(@NonNull String email){
        User user = userService.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User doesn't exist in our DB");
        }
        return JWTUserFactory.create(user);
    }
}
