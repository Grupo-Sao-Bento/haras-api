package com.sbgroup.haras.repositories;

import com.sbgroup.haras.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

  @Query("SELECT c FROM Client c WHERE c.firstName = :firstName AND c.lastName = :lastName")
  List<Client> findNameAndLastName(String firstName, String lastName);

}
