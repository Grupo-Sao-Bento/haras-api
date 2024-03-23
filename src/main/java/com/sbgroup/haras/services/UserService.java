package com.sbgroup.haras.services;

import com.sbgroup.haras.dtos.UserDTO;
import com.sbgroup.haras.models.User;
import com.sbgroup.haras.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
  
  @Autowired()
  UserRepository userRepository;
  
  @Transactional()
  public ResponseEntity<User> saveUser(UserDTO newUserDto) {
    var newUserModel = new User();
    BeanUtils.copyProperties(newUserDto, newUserModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(newUserModel));
  }
  
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
  }
  
  public ResponseEntity<Object> getUserById(UUID userId) {
    Optional<User> userModel = userRepository.findById(userId);
    
    if (userModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id not found");
    }
    
    return ResponseEntity.status(HttpStatus.OK).body(userModel);
  }
  
  @Transactional()
  public ResponseEntity<Object> updateUserById(UserDTO userDto, UUID userId) {
    Optional<User> userModel = userRepository.findById(userId);
    
    if (userModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id not found");
    }
    
    var updatedUser = userModel.get();
    BeanUtils.copyProperties(userDto, updatedUser);
    return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(updatedUser));
  }
  
  @Transactional()
  public ResponseEntity<Object> deleteUserById(UUID userId) {
    Optional<User> userModel = userRepository.findById(userId);
    
    if (userModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id not found");
    }
    
    userRepository.delete(userModel.get());
    return ResponseEntity.status(HttpStatus.OK).body("User deleted successfuly");
  }
}
