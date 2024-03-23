package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.UserDTO;
import com.sbgroup.haras.models.User;
import com.sbgroup.haras.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
  
  @Autowired()
  UserService userService;
  
  @PostMapping()
  public ResponseEntity<User> saveUser(@RequestBody @Valid UserDTO newUserDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(newUserDto));
  }
  
  @GetMapping()
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Object> getUserById(@PathVariable(value = "id") UUID userId) {
    Optional<User> userModel = userService.getUserById(userId);

    if (userModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id not found");
    }

    return ResponseEntity.status(HttpStatus.OK).body(userModel);
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateUserById(@RequestBody @Valid UserDTO userDto,
                                               @PathVariable(value = "id") UUID userId) {
    Optional<User> userModel = userService.updateUserById(userDto, userId);

    if (userModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id not found");
    }

    return ResponseEntity.status(HttpStatus.OK).body(userModel);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteUserById(@PathVariable(value = "id") UUID userId) {
    Optional<User> userModel = userService.deleteUserById(userId);

    if (userModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id not found");
    }

    return ResponseEntity.status(HttpStatus.OK).body("User deleted successfuly");
  }
}
