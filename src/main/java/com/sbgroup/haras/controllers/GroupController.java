package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.GroupRecordDTO;
import com.sbgroup.haras.models.GroupModel;
import com.sbgroup.haras.services.GroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired()
    GroupService groupService;

    @PostMapping()
    public ResponseEntity<GroupModel> saveGroup(@RequestBody @Valid GroupRecordDTO newGroupDto) {
        return groupService.saveGroup(newGroupDto);
    }

    @GetMapping()
    public ResponseEntity<List<GroupModel>> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGroupById(@PathVariable(value = "id") UUID groupId) {
        return groupService.getGroupById(groupId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGroupById(@RequestBody @Valid GroupRecordDTO groupDto,
                                                  @PathVariable(value = "id") UUID groupId) {
        return groupService.updateGroupById(groupDto, groupId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGroupById(@PathVariable(value = "id") UUID groupId) {
        return groupService.deleteGroupById(groupId);
    }
}
