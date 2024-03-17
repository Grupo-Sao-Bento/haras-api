package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.AuthDTO;
import com.sbgroup.haras.dtos.LoginTokenDTO;
import com.sbgroup.haras.dtos.UserDTO;
import com.sbgroup.haras.models.UserModel;
import com.sbgroup.haras.repositories.AuthRepository;
import com.sbgroup.haras.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private AuthRepository authRepository;

  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Valid AuthDTO authData) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(authData.login(), authData.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);

    var token = tokenService.generateToken((UserModel) auth.getPrincipal());

    return ResponseEntity.ok(new LoginTokenDTO(token));
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody @Valid UserDTO data) {
    if (this.authRepository.findByLogin(data.email()) != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");

    } else {
      String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
      Timestamp now = Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.of("-03:00")));

      UserModel newAuth = new UserModel(
        data.firstName(),
        data.lastName(),
        data.email(),
        encryptedPassword,
        data.role(),
        "test_farm",
        now);

      this.authRepository.save(newAuth);

      return ResponseEntity.status(HttpStatus.OK).body("User created successfully");
    }
  }
}
