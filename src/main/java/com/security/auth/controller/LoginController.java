package com.security.auth.controller;

import com.security.auth.dto.LoginDTO;
import com.security.auth.dto.LoginResponse;
import com.security.auth.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO){
        LoginResponse loginResponse = loginService.login(loginDTO);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

}
