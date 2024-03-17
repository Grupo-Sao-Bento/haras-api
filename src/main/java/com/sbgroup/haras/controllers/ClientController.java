package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.ClientDTO;
import com.sbgroup.haras.models.ClientModel;
import com.sbgroup.haras.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {
  
  @Autowired()
  ClientService clientService;
  
  @PostMapping()
  public ResponseEntity<ClientModel> saveClient(@RequestBody @Valid ClientDTO newClientDto) {
    return clientService.saveClient(newClientDto);
  }
  
  @GetMapping()
  public ResponseEntity<List<ClientModel>> getAllClients() {
    return clientService.getAllClients();
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Object> getClientById(@PathVariable(value = "id") UUID clientId) {
    return clientService.getClientById(clientId);
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateClientById(@RequestBody @Valid ClientDTO clientDto,
                                                 @PathVariable(value = "id") UUID clientId) {
    return clientService.updateClientById(clientDto, clientId);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteClientById(@PathVariable(value = "id") UUID clientId) {
    return clientService.deleteClientById(clientId);
  }
}
