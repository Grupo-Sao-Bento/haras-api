package com.sbgroup.haras.repositories;

import com.sbgroup.haras.models.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StayRepository extends JpaRepository<Stay, UUID> {
}
