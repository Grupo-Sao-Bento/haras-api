package com.sbgroup.haras.repositories;

import com.sbgroup.haras.models.ProcedureModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcedureRepository extends JpaRepository<ProcedureModel, UUID> {
}
