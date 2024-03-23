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
  public User saveUser(UserDTO newUserDto) {
    var newUserModel = new User();
    BeanUtils.copyProperties(newUserDto, newUserModel);
    return newUserModel;
  }
  
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
  
  public Optional<User> getUserById(UUID userId) {
    return userRepository.findById(userId);
  }
  
  @Transactional()
  public Optional<User> updateUserById(UserDTO userDto, UUID userId) {
    var userModel = userRepository.findById(userId);

    if (userModel.isEmpty()) {
      return Optional.empty();
    }
    
    var updatedUser = userModel.get();
    BeanUtils.copyProperties(userDto, updatedUser);
    return Optional.of(userRepository.save(updatedUser));
  }
  
  @Transactional()
  public Optional<User> deleteUserById(UUID userId) {
    var userModel = userRepository.findById(userId);
    
    if (userModel.isEmpty()) {
      return Optional.empty();
    }

    userRepository.delete(userModel.get());
    return userModel;
  }
}
