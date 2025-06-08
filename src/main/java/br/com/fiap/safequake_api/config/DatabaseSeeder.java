package br.com.fiap.safequake_api.config;

import br.com.fiap.safequake_api.model.EarthquakeEvent;
import br.com.fiap.safequake_api.model.User;
import br.com.fiap.safequake_api.repository.EarthquakeEventRepository;
import br.com.fiap.safequake_api.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final EarthquakeEventRepository earthquakeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void seed() {
        seedEarthquakes();
        seedUsers();
    }

    private void seedEarthquakes() {
        if (earthquakeRepository.count() > 0) return;

        earthquakeRepository.save(EarthquakeEvent.builder()
                .latitude(-23.5505)
                .longitude(-46.6333)
                .magnitude(4.7)
                .timestamp(LocalDateTime.now().minusDays(1))
                .externalId(UUID.randomUUID().toString())
                .place("São Paulo, Brasil")
                .build());

        earthquakeRepository.save(EarthquakeEvent.builder()
                .latitude(35.6895)
                .longitude(139.6917)
                .magnitude(6.2)
                .timestamp(LocalDateTime.now().minusDays(2))
                .externalId(UUID.randomUUID().toString())
                .place("Tóquio, Japão")
                .build());

        earthquakeRepository.save(EarthquakeEvent.builder()
                .latitude(37.7749)
                .longitude(-122.4194)
                .magnitude(5.5)
                .timestamp(LocalDateTime.now().minusHours(12))
                .externalId(UUID.randomUUID().toString())
                .place("San Francisco, EUA")
                .build());

        earthquakeRepository.save(EarthquakeEvent.builder()
                .latitude(-33.4489)
                .longitude(-70.6693)
                .magnitude(7.1)
                .timestamp(LocalDateTime.now().minusDays(3))
                .externalId(UUID.randomUUID().toString())
                .place("Santiago, Chile")
                .build());

        earthquakeRepository.save(EarthquakeEvent.builder()
                .latitude(19.4326)
                .longitude(-99.1332)
                .magnitude(2.4)
                .timestamp(LocalDateTime.now().minusHours(6))
                .externalId(UUID.randomUUID().toString())
                .place("Cidade do México")
                .build());
    }

    private void seedUsers() {
        if (userRepository.count() > 0) return;

        userRepository.save(User.builder()
                .name("Alice")
                .email("alice@fiap.com.br")
                .password(passwordEncoder.encode("123456"))
                .latitude(-23.5505)
                .longitude(-46.6333)
                .build());

        userRepository.save(User.builder()
                .name("Bob")
                .email("bob@fiap.com.br")
                .password(passwordEncoder.encode("123456"))
                .build());

        userRepository.save(User.builder()
                .name("Carlos")
                .email("carlos@fiap.com.br")
                .password(passwordEncoder.encode("123456"))
                .latitude(37.7749)
                .longitude(-122.4194)
                .build());

        userRepository.save(User.builder()
                .name("Joao")
                .email("joao@fiap.com.br")
                .password(passwordEncoder.encode("123456"))
                .latitude(-33.4489)
                .longitude(-70.6693)
                .build());

        userRepository.save(User.builder()
                .name("Ana")
                .email("ana@fiap.com.br")
                .password(passwordEncoder.encode("123456"))
                .latitude(19.4326)
                .longitude(-99.1332)
                .build());
    }
}
