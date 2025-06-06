package br.com.fiap.safequake_api.controller;

import br.com.fiap.safequake_api.dto.UserRequestDTO;
import br.com.fiap.safequake_api.dto.UserResponseDTO;
import br.com.fiap.safequake_api.model.User;
import br.com.fiap.safequake_api.repository.UserRepository;
import br.com.fiap.safequake_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Autowired
    private UserRepository repository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public User create(@RequestBody @Valid UserRequestDTO dto) {
        User user = User.builder()
            .name(dto.getName())
            .email(dto.getEmail())
            .password(passwordEncoder.encode(dto.getPassword()))
            .latitude(dto.getLatitude())
            .longitude(dto.getLongitude())
            .build();

        return repository.save(user);
    }


}

