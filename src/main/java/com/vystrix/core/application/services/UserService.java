package com.vystrix.core.application.services;

import com.vystrix.core.application.dto.AccountDTO;
import com.vystrix.core.application.dto.UserAccountDTO;
import com.vystrix.core.application.dto.UserDTO;
import com.vystrix.core.application.mapper.UserMapper;
import com.vystrix.core.domain.dto.UserCreateDTO;
import com.vystrix.core.domain.entities.User;
import com.vystrix.core.infrastructure.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AccountService accountService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO getUserById(Long id){
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));
    }

    @Transactional
    public UserAccountDTO createUser(UserCreateDTO userCreateDTO){
        if(userRepository.findByEmail(userCreateDTO.email()).isPresent()){
            throw new EntityExistsException("E-mail já cadastrado!");
        }

        User savedUser = userRepository.save(userMapper.toEntity(userCreateDTO, passwordEncoder));
        AccountDTO accountDTO = accountService.createAccount(savedUser);

        return userMapper.toUserAccountDTO(savedUser, accountDTO);
    }
}
