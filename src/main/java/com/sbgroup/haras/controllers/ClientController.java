package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.ClientDTO;
import com.sbgroup.haras.models.Client;
import com.sbgroup.haras.models.User;
import com.sbgroup.haras.services.ClientService;
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
@RequestMapping("/clients")
public class ClientController {
  
  @Autowired()
  private ClientService clientService;

  @Autowired()
  private UserService userService;

  @PostMapping()
  public ResponseEntity<Object> register(@RequestBody @Valid ClientDTO clientDTO, HttpServletRequest request) {
    String token = request.getHeader("Authorization").replace("Bearer ", "");
    Optional<User> user = userService.getUserByToken(token);

    if (user.isPresent()) {
      User userModel = user.get();
      return ResponseEntity.status(HttpStatus.OK).body(clientService.registerClient(clientDTO, userModel));

    } else {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error identifying user by token");
    }
  }
  @GetMapping()
  public ResponseEntity<PaginatedResponseUtil<Client>> getAllClients(
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size ) {
    List<Client> clients = clientService.getAllClients(page, size);

    PaginatedResponseUtil<Client> paginatedResponse = new PaginatedResponseUtil<>(
      clients,
      page,
      clientService.getClientsTotalPages(size),
      clientService.getClientsTotalElements()
    );

    return ResponseEntity.status(HttpStatus.OK).body(paginatedResponse);
  }

  @GetMapping("/firstName/{firstName}")
  public ResponseEntity<Object> getClientByFirstName(@PathVariable(value = "firstName") String clientFirstName) {
    List<Client> clients = clientService.getClientByFirstName(clientFirstName);

    return ResponseEntity.status(HttpStatus.OK).body(clients);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getClientById(@PathVariable(value = "id") UUID clientId) {
    Optional<Client> client = clientService.getClientById(clientId);

    if (client.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client id not found");
    }

    return ResponseEntity.status(HttpStatus.OK).body(client);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateClientById(@RequestBody @Valid ClientDTO clientDTO,
                   @PathVariable(value = "id") UUID clientId, HttpServletRequest request) {
    String token = request.getHeader("Authorization").replace("Bearer ", "");
    Optional<User> user = userService.getUserByToken(token);

    if (user.isPresent()) {
      User userModel = user.get();
      Optional<Client> client = clientService.updateClientById(clientDTO, clientId, userModel);

      if (client.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client id not found");
      }

      return ResponseEntity.status(HttpStatus.OK).body(client);

    } else {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error identifying user by token");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteClientById(@PathVariable(value = "id") UUID clientId) {
    Optional<Client> client = clientService.deleteClientById(clientId);

    if (client.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client id not found");
    }

    return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfuly");
  }

}
