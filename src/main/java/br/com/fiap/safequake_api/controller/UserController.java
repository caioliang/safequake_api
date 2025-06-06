package br.com.fiap.safequake_api.controller;

import br.com.fiap.safequake_api.dto.UserRequestDTO;
import br.com.fiap.safequake_api.dto.UserResponseDTO;
import br.com.fiap.safequake_api.model.User;
import br.com.fiap.safequake_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // @PostMapping
    // public ResponseEntity<User> create(@RequestBody @Valid UserRequestDTO dto) {
    //     User user = userService.create(dto);
    //     return ResponseEntity.status(201).body(user);
    // }
        @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO dto) {
        User user = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(user));
    }

    private UserResponseDTO toResponse(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .latitude(user.getLatitude())
                .longitude(user.getLongitude())
                .build();
    }
}

