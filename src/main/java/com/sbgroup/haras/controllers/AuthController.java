package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.AuthDTO;
import com.sbgroup.haras.dtos.LoginTokenDTO;
import com.sbgroup.haras.dtos.UserDTO;
import com.sbgroup.haras.models.User;
import com.sbgroup.haras.security.TokenService;
import com.sbgroup.haras.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private AuthService authService;

  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody @Valid AuthDTO authData) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(authData.login(), authData.password());
    var auth = authenticationManager.authenticate(usernamePassword);
    var token = tokenService.generateToken((User) auth.getPrincipal());

    return ResponseEntity.status(HttpStatus.OK).body(new LoginTokenDTO(token));
  }

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody @Valid UserDTO userDTO) {
    if (authService.loadUserByUsername(userDTO.login()) != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");

    } else {
      return ResponseEntity.status(HttpStatus.OK).body(authService.registerUser(userDTO));
    }
  }
  
}
