package com.sbgroup.haras.repositories;

import com.sbgroup.haras.models.HorseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HorseRepository extends JpaRepository<HorseModel, UUID> {
}
