package com.sbgroup.haras.repositories;

import com.sbgroup.haras.models.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RolesRepository extends JpaRepository<RolesModel, UUID> {
}
