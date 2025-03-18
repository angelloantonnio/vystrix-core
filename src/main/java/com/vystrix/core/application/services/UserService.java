package com.vystrix.core.application.services;

import com.vystrix.core.application.dto.UserDTO;
import com.vystrix.core.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO getUserById(Long id){
        return userRepository.findById(id)
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail()))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }
}
