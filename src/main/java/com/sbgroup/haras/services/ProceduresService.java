package com.sbgroup.haras.services;

import com.sbgroup.haras.dtos.ProceduresDTO;
import com.sbgroup.haras.models.Animal;
import com.sbgroup.haras.models.Procedures;
import com.sbgroup.haras.models.User;
import com.sbgroup.haras.repositories.AnimalRepository;
import com.sbgroup.haras.repositories.ProceduresRepository;
import com.sbgroup.haras.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProceduresService {

    @Autowired
    private ProceduresRepository proceduresRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Transactional
    public Procedures registerProcedures(ProceduresDTO proceduresDTO, User authUser, UUID animalId) {
        var newProcedures = new Procedures();
        BeanUtils.copyProperties(proceduresDTO, newProcedures);
        newProcedures.setCreatedAt(TimeUtil.getCurrentTimestamp());
        newProcedures.setResponsibleName(authUser);

        Optional<Animal> animal = animalRepository.findById(animalId);
        newProcedures.setAnimal(animal.get());

        return proceduresRepository.save(newProcedures);
    }

    @Transactional
    public Optional<Procedures> updateProcedureById(ProceduresDTO proceduresDTO, UUID proceduresId, User authUser, UUID animalId) {
        var procedures = proceduresRepository.findById(proceduresId);

        if (procedures.isEmpty()) {
            return Optional.empty();
        }

        var updatedProcedures = procedures.get();
        BeanUtils.copyProperties(proceduresDTO, updatedProcedures);
        updatedProcedures.setUpdateAt(TimeUtil.getCurrentTimestamp());
        updatedProcedures.setUpdateBy(authUser);

        Optional<Animal> animal = animalRepository.findById(animalId);
        updatedProcedures.setAnimal(animal.get());

        return Optional.of(proceduresRepository.save(updatedProcedures));
    }

    @Transactional()
    public Optional<Procedures> deleteProcedureById(UUID procedureId) {
        var procedure = proceduresRepository.findById(procedureId);

        if (procedure.isEmpty()) {
            return Optional.empty();
        }

        proceduresRepository.delete(procedure.get());
        return procedure;
    }

    public List<Procedures> getAllProcedures(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Procedures> proceduresPage = proceduresRepository.findAll(pageable);

        return proceduresPage.getContent();
    }

    public long getProceduresTotalElements() {
        return proceduresRepository.count();
    }

    public int getProceduresTotalPages(int size) {
        Pageable pageable = PageRequest.of(0, size);
        long totalElements = getProceduresTotalElements();

        return (int) Math.ceil((double) totalElements / (double) size);
    }

    public Optional<Procedures> getProceduresById(UUID proceduresId) {
        return proceduresRepository.findById(proceduresId);
    }
}
