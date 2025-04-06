package com.vystrix.core.application.services;

import com.vystrix.core.application.dto.AuthResponseDTO;
import com.vystrix.core.application.exception.ForbiddenOperationException;
import com.vystrix.core.domain.dto.AuthRequestDTO;
import com.vystrix.core.domain.entities.User;
import com.vystrix.core.infrastructure.repositories.UserRepository;
import com.vystrix.core.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.email(), authRequestDTO.password())
        );

        User user = userRepository.findByEmail(authRequestDTO.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponseDTO(token);
    }

    public String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
