package com.vystrix.core.adapters.controllers;

import com.vystrix.core.application.dto.UserAccountDTO;
import com.vystrix.core.application.dto.UserDTO;
import com.vystrix.core.application.services.UserService;
import com.vystrix.core.domain.dto.UserCreateDTO;
import com.vystrix.core.domain.dto.UserUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserDetails(){
        return ResponseEntity.ok(userService.getUserDetails());
    }

    @PostMapping
    public ResponseEntity<UserAccountDTO> createUser(@RequestBody @Valid UserCreateDTO userCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userCreateDTO));
    }

    @PutMapping("/me")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserUpdateDTO userUpdateDTO){
        return ResponseEntity.ok(userService.updateUser(userUpdateDTO));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser(){
        userService.deleteUser();
        return ResponseEntity.noContent().build();
    }
}
