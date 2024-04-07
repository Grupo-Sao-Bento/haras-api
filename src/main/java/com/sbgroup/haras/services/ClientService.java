package com.sbgroup.haras.services;

import com.sbgroup.haras.dtos.ClientDTO;
import com.sbgroup.haras.models.Client;
import com.sbgroup.haras.models.User;
import com.sbgroup.haras.repositories.ClientRepository;
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
public class ClientService {
  
  @Autowired()
  private ClientRepository clientRepository;

  @Transactional()
  public Client registerClient(ClientDTO clientDTO, User authUser) {
    var newClient = new Client();

    BeanUtils.copyProperties(clientDTO, newClient);
    newClient.setCreatedAt(TimeUtil.getCurrentTimestamp());
    newClient.setCreatedBy(authUser);

    return clientRepository.save(newClient);
  }

  public List<Client> getAllClients(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Client> clientPage = clientRepository.findAll(pageable);

    return clientPage.getContent();
  }

  public long getClientsTotalElements() {
    return clientRepository.count();
  }

  public int getClientsTotalPages(int size) {
    Pageable pageable = PageRequest.of(0, size);
    long totalElements = getClientsTotalElements();

    return (int) Math.ceil((double) totalElements / (double) size);
  }

  public List<Client> getClientByCpf(String cpf) {
    return clientRepository.findCpf(cpf);
  }

  public List<Client> getClientByCnpj(String cnpj) {
    return clientRepository.findCnpj(cnpj);
  }
  
  public Optional<Client> getClientById(UUID clientId) {
    return clientRepository.findById(clientId);
  }

  @Transactional()
  public Optional<Client> updateClientById(ClientDTO clientDTO, UUID clientId, User authUser) {
    var client = clientRepository.findById(clientId);

    if (client.isEmpty()) {
      return Optional.empty();
    }

    var updatedClient = client.get();
    BeanUtils.copyProperties(clientDTO, updatedClient);
    updatedClient.setUpdateAt(TimeUtil.getCurrentTimestamp());
    updatedClient.setUpdateBy(authUser);

    return Optional.of(clientRepository.save(updatedClient));
  }

  @Transactional()
  public Optional<Client> deleteClientById(UUID clientId) {
    var client = clientRepository.findById(clientId);

    if (client.isEmpty()) {
      return Optional.empty();
    }

    clientRepository.delete(client.get());
    return client;
  }

}
