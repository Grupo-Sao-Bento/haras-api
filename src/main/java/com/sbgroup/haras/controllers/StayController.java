package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.StayDTO;
import com.sbgroup.haras.models.Stay;
import com.sbgroup.haras.models.User;
import com.sbgroup.haras.services.StayService;
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
@RequestMapping("/stays")
public class StayController {

    @Autowired()
    private StayService stayService;

    @Autowired()
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Object> register(@RequestBody @Valid StayDTO stayDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Optional<User> user = userService.getUserByToken(token);

        User userModel = user.get();

        return ResponseEntity.status(HttpStatus.OK).body(stayService.createStay(stayDTO, userModel, stayDTO.animal()));
    }

    @GetMapping()
    public ResponseEntity<PaginatedResponseUtil<Stay>> getAllStays(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Stay> stay = stayService.getAllStays(page, size);

        PaginatedResponseUtil<Stay> paginatedResponse = new PaginatedResponseUtil<>(
                stay,
                page,
                stayService.getStaysTotalPages(size),
                stayService.getStaysTotalElements());

        return ResponseEntity.status(HttpStatus.OK).body(paginatedResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStayById(@PathVariable(value = "id") UUID stayId) {
        Optional<Stay> stay = stayService.getStayById(stayId);

        if (stay.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stay id not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(stay);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStayById(@RequestBody @Valid StayDTO stayDTO,
                                                 @PathVariable(value = "id") UUID stayId, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Optional<User> user = userService.getUserByToken(token);

        User userModel = user.get();

        Optional<Stay> stay = stayService.updateStayById(stayDTO, stayId, userModel, stayDTO.animal());

        if (stay.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stay id not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(stay);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStayById(@PathVariable(value = "id") UUID stayId) {
        Optional<Stay> stay = stayService.deleteStayById(stayId);

        if (stay.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stay id not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Stay deleted successfuly");
    }

}
