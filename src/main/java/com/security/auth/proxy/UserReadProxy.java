package com.security.auth.proxy;

import com.security.auth.model.User;

import java.util.List;

public interface UserReadProxy {
    List<User> findAll();
}
