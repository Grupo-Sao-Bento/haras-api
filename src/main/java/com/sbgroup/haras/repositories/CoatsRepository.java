package com.sbgroup.haras.repositories;

import com.sbgroup.haras.models.CoatsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CoatsRepository extends JpaRepository<CoatsModel, UUID> {
}
