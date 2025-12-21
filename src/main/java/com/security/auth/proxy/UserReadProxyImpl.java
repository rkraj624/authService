package com.security.auth.proxy;

import com.security.auth.model.User;
import com.security.auth.service.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReadProxyImpl implements UserReadProxy {

    private final UserService userService;

    public UserReadProxyImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AccessDeniedException("Access denied");
        }

        boolean allowed = authentication.getAuthorities().stream()
                .anyMatch(a -> "ROLE_USER".equals(a.getAuthority()) || "ROLE_ADMIN".equals(a.getAuthority()));
        if (!allowed) {
            throw new AccessDeniedException("Access denied");
        }
        return userService.findAll();
    }
}
