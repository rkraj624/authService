package com.security.auth.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.jspecify.annotations.NonNull;


import java.io.Serial;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends CommonDataModel {

    @Serial
    private static final long serialVersionUID = 2141479273835303378L;

    private String name;

    private String email;

    private String password;

    private Set<String> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

}
