package com.sbgroup.haras.services;

import com.sbgroup.haras.dtos.UserDTO;
import com.sbgroup.haras.models.User;
import com.sbgroup.haras.repositories.AuthRepository;
import com.sbgroup.haras.utils.TimeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

  @Autowired
  private AuthRepository authRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return authRepository.findByLogin(username);
  }
  
  public User registerUser(UserDTO userDTO) {
    String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
    
    User newAuth = new User(
      userDTO.firstName(),
      userDTO.lastName(),
      userDTO.login(),
      encryptedPassword,
      userDTO.role(),
      TimeUtil.getCurrentTimestamp()
    );

    return authRepository.save(newAuth);
  }
}
