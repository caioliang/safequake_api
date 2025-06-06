package br.com.fiap.safequake_api.service;

import br.com.fiap.safequake_api.dto.UserRequestDTO;
import br.com.fiap.safequake_api.model.User;
import br.com.fiap.safequake_api.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(@Valid UserRequestDTO dto) {
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword()) 
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();

        return userRepository.save(user);
    }
}
