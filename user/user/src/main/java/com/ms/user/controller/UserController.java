package com.ms.user.controller;

import com.ms.user.dto.UserRecordDto;
import com.ms.user.models.UserModel;
import com.ms.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveSer(@RequestBody @Valid UserRecordDto userRecordDto) {
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }

    @GetMapping("/users")
    public List<UserModel> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{UUID}")
    public ResponseEntity<Optional<UserModel>> getByUUuid(@PathVariable("UUID") UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getByUuid(uuid));
    }

    @PutMapping("/users"){
        public ResponseEntity<UserModel> editUser(@RequestBody)
    }
}
