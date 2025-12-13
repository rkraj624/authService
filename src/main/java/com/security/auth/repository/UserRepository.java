package com.security.auth.repository;
import com.security.auth.model.User;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CommonJPARepository<User, String> {

    Optional<User> findByEmail(String email);
}
