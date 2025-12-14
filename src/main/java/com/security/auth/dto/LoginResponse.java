package com.security.auth.dto;

import com.security.auth.model.User;

public class LoginResponse {
    private String token;

    private User user;

    private LoginResponse(){

    }
    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder {
        private String token;

        private User user;

        private Builder(){

        }

        public Builder token(String token){
            this.token = token;
            return this;
        }

        public Builder user(User user){
            this.user = user;
            return this;
        }

        public LoginResponse build(){
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setUser(user);
            return loginResponse;
        }

    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
