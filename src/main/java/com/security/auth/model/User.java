package com.security.auth.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.jspecify.annotations.NonNull;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends CommonDataModel {

    @Serial
    private static final long serialVersionUID = 2141479273835303378L;

    private String name;
    @Column(unique = true)
    private String email;

    private String password;

    private Set<String> roles;

    @Override
    public int hashCode() {
        return getEmail() != null ? getEmail().hashCode() : super.hashCode();
    }

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

    public List<String> getRoles() {
        return new ArrayList<>(roles);
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

}
