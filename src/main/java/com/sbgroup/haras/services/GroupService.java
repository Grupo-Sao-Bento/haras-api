package com.sbgroup.haras.services;

import com.sbgroup.haras.dtos.GroupDTO;
import com.sbgroup.haras.models.GroupModel;
import com.sbgroup.haras.repositories.GroupRepository;
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
public class GroupService {
  
  @Autowired()
  GroupRepository groupRepository;
  
  @Transactional()
  public ResponseEntity<GroupModel> saveGroup(GroupDTO newGroupDto) {
    var newGroupModel = new GroupModel();
    BeanUtils.copyProperties(newGroupDto, newGroupModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(groupRepository.save(newGroupModel));
  }
  
  public ResponseEntity<List<GroupModel>> getAllGroups() {
    return ResponseEntity.status(HttpStatus.OK).body(groupRepository.findAll());
  }
  
  public ResponseEntity<Object> getGroupById(UUID groupId) {
    Optional<GroupModel> groupModel = groupRepository.findById(groupId);
    
    if (groupModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group id not found");
    }
    
    return ResponseEntity.status(HttpStatus.OK).body(groupModel);
  }
  
  @Transactional()
  public ResponseEntity<Object> updateGroupById(GroupDTO groupDto, UUID groupId) {
    Optional<GroupModel> groupModel = groupRepository.findById(groupId);
    
    if (groupModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group id not found");
    }
    
    var updatedGroup = groupModel.get();
    BeanUtils.copyProperties(groupDto, updatedGroup);
    return ResponseEntity.status(HttpStatus.OK).body(groupRepository.save(updatedGroup));
  }
  
  @Transactional()
  public ResponseEntity<Object> deleteGroupById(UUID groupId) {
    Optional<GroupModel> groupModel = groupRepository.findById(groupId);
    
    if (groupModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group id not found");
    }
    
    groupRepository.delete(groupModel.get());
    return ResponseEntity.status(HttpStatus.OK).body("Group deleted successfuly");
  }
}
