package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.ProceduresDTO;
import com.sbgroup.haras.models.Procedures;
import com.sbgroup.haras.models.User;
import com.sbgroup.haras.services.ProceduresService;
import com.sbgroup.haras.services.UserService;
import com.sbgroup.haras.utils.PaginatedResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/procedures")
public class ProceduresController {

    @Autowired()
    private ProceduresService proceduresService;

    @Autowired()
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody @Valid ProceduresDTO proceduresDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Optional<User> user = userService.getUserByToken(token);

        User userModel = user.get();

        return ResponseEntity.status(HttpStatus.OK).body(proceduresService.registerProcedures(proceduresDTO, userModel, proceduresDTO.animalId()));

    }

    @GetMapping
    public ResponseEntity<PaginatedResponseUtil<Procedures>> getAllProcedures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Procedures> procedures = proceduresService.getAllProcedures(page, size);

        PaginatedResponseUtil<Procedures> paginatedResponse = new PaginatedResponseUtil<>(
                procedures,
                page,
                proceduresService.getProceduresTotalPages(size),
                proceduresService.getProceduresTotalElements());

        return ResponseEntity.status(HttpStatus.OK).body(paginatedResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProcedureById(@PathVariable(value = "id") UUID procedureId) {
        Optional<Procedures> procedures = proceduresService.getProceduresById(procedureId);

        if (procedures.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Procedure id not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(procedures);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProcedureById(@RequestBody @Valid ProceduresDTO proceduresDTO,
                                                      @PathVariable(value = "id") UUID procedureId, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Optional<User> user = userService.getUserByToken(token);

        User userModel = user.get();

        Optional<Procedures> procedure = proceduresService.updateProcedureById(proceduresDTO, procedureId, userModel);

        if (procedure.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Procedure id not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(procedure);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProcedureById(@PathVariable(value = "id") UUID procedureId) {
        Optional<Procedures> procedure = proceduresService.deleteProcedureById(procedureId);

        if (procedure.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Procedure id not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Procedure deleted successfuly");
    }

}
