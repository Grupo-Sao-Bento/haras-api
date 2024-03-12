package com.sbgroup.haras.services;

import com.sbgroup.haras.dtos.UserRecordDTO;
import com.sbgroup.haras.models.UserModel;
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
  public ResponseEntity<UserModel> saveUser(UserRecordDTO newUserDto) {
    var newUserModel = new UserModel();
    BeanUtils.copyProperties(newUserDto, newUserModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(newUserModel));
  }
  
  public ResponseEntity<List<UserModel>> getAllUsers() {
    return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
  }
  
  public ResponseEntity<Object> getUserById(UUID userId) {
    Optional<UserModel> userModel = userRepository.findById(userId);
    
    if (userModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id not found");
    }
    
    return ResponseEntity.status(HttpStatus.OK).body(userModel);
  }
  
  @Transactional()
  public ResponseEntity<Object> updateUserById(UserRecordDTO userDto, UUID userId) {
    Optional<UserModel> userModel = userRepository.findById(userId);
    
    if (userModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id not found");
    }
    
    var updatedUser = userModel.get();
    BeanUtils.copyProperties(userDto, updatedUser);
    return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(updatedUser));
  }
  
  @Transactional()
  public ResponseEntity<Object> deleteUserById(UUID userId) {
    Optional<UserModel> userModel = userRepository.findById(userId);
    
    if (userModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User id not found");
    }
    
    userRepository.delete(userModel.get());
    return ResponseEntity.status(HttpStatus.OK).body("User deleted successfuly");
  }
}
