package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.UserRecordDTO;
import com.sbgroup.haras.models.UserModel;
import com.sbgroup.haras.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired()
    UserService userService;

    @PostMapping()
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDTO newUserDto) {
        return userService.saveUser(newUserDto);
    }

    @GetMapping()
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") UUID userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUserById(@RequestBody @Valid UserRecordDTO userDto,
                                                  @PathVariable(value = "id") UUID userId) {
        return userService.updateUserById(userDto, userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "id") UUID userId) {
        return userService.deleteUserById(userId);
    }
}
