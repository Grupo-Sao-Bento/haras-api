package com.sbgroup.haras.repositories;

import com.sbgroup.haras.models.Farm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FarmRepository extends JpaRepository<Farm, UUID> {
  
  List<Farm> findAllByName(String name);
  
}