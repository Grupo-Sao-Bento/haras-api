package com.sbgroup.haras;

import com.sbgroup.haras.dtos.UserDTO;
import com.sbgroup.haras.enums.UserRole;
import com.sbgroup.haras.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HarasApplication {

  // TODO: Remove in production
  @Bean
  public CommandLineRunner init(@Autowired AuthService authService) {
    return args -> {
      if (authService.loadUserByUsername("master") == null) {

        UserDTO masterUser = new UserDTO(
          "master@email.com",
          "master-user",
          "master-user",
          UserRole.MASTER,
          "master"
        );

        authService.registerUser(masterUser);
      }

      System.out.println(
        """
          
          =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          
           CAUTION: Master User registered automatically!
           
           LOGIN: master
           PASSWORD: master
           
           REMOVE METHOD BEFORE RUN IN PRODUCTION:
           'com.sbgroup.haras/HarasApplication.java:init'
           
          =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
           
         """);

    };
  }

  public static void main(String[] args) {
    SpringApplication.run(HarasApplication.class, args);
  }
  
}
