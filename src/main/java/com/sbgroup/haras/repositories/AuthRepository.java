package com.sbgroup.haras.repositories;

import com.sbgroup.haras.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<UserModel, UUID> {

  UserDetails findByLogin(String login);
}
