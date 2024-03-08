package com.sbgroup.haras.services;

import com.sbgroup.haras.dtos.ClientRecordDTO;
import com.sbgroup.haras.models.ClientModel;
import com.sbgroup.haras.repositories.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired()
    ClientRepository clientRepository;

    @Transactional()
    public ResponseEntity<ClientModel> saveClient(ClientRecordDTO newClientDto) {
        var newClientModel = new ClientModel();
        BeanUtils.copyProperties(newClientDto, newClientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(newClientModel));
    }

    public ResponseEntity<List<ClientModel>> getAllClients() {
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findAll());
    }

    public ResponseEntity<Object> getClientById(UUID clientId) {
        Optional<ClientModel> clientModel = clientRepository.findById(clientId);

        if (clientModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client id not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(clientModel);
    }

    @Transactional()
    public ResponseEntity<Object> updateClientById(ClientRecordDTO clientDto, UUID clientId) {
        Optional<ClientModel> clientModel = clientRepository.findById(clientId);

        if (clientModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client id not found");
        }

        var updatedClient = clientModel.get();
        BeanUtils.copyProperties(clientDto, updatedClient);
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.save(updatedClient));
    }

    @Transactional()
    public ResponseEntity<Object> deleteClientById(UUID clientId) {
        Optional<ClientModel> clientModel = clientRepository.findById(clientId);

        if (clientModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client id not found");
        }

        clientRepository.delete(clientModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfuly");
    }
}
