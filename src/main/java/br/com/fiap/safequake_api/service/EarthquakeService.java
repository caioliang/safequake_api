package br.com.fiap.safequake_api.service;

import br.com.fiap.safequake_api.dto.EarthquakeEventFullResponseDTO;
import br.com.fiap.safequake_api.dto.EarthquakeEventRequestDTO;
import br.com.fiap.safequake_api.dto.EarthquakeEventResponseDTO;
import br.com.fiap.safequake_api.model.EarthquakeEvent;
import br.com.fiap.safequake_api.model.User;
import br.com.fiap.safequake_api.repository.EarthquakeEventRepository;
import br.com.fiap.safequake_api.repository.UserRepository;
import br.com.fiap.safequake_api.util.GeoUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EarthquakeService {

    private final EarthquakeEventRepository earthquakeRepository;
    private final UserRepository userRepository;
    private final AlertService alertService;

    private static final double ALERT_RADIUS_KM = 100.0;
    private static final double ALERT_THRESHOLD = 2.5;

    @Transactional
    public EarthquakeEventResponseDTO createManual(EarthquakeEventRequestDTO dto) {
        EarthquakeEvent event = fromRequestDto(dto);
        event.setExternalId("manual_" + UUID.randomUUID());
        EarthquakeEvent saved = earthquakeRepository.save(event);

        double score = GeoUtils.calcularIntensidade(event.getMagnitude(), event.getDepth());

        if (score > ALERT_THRESHOLD) {
            List<User> proximos = encontrarUsuariosProximos(event.getLatitude(), event.getLongitude());
            for (User user : proximos) {
                String nivel = GeoUtils.definirNivel(score);
                alertService.emitirAlerta(user, saved, nivel);
            }
        }

        return toResponseDto(saved);
    }

    public List<EarthquakeEventResponseDTO> findAll() {
        return earthquakeRepository.findAll().stream()
            .map(this::toResponseDto)
            .collect(Collectors.toList());
    }

    public List<EarthquakeEventFullResponseDTO> findAllWithClassification() {
    return earthquakeRepository.findAll().stream()
            .map(eq -> {
                double intensidade = GeoUtils.calcularIntensidade(eq.getMagnitude(), eq.getDepth());
                String nivel = GeoUtils.definirNivel(intensidade);
                return EarthquakeEventFullResponseDTO.builder()
                        .id(eq.getId())
                        .latitude(eq.getLatitude())
                        .longitude(eq.getLongitude())
                        .magnitude(eq.getMagnitude())
                        .depth(eq.getDepth())
                        .timestamp(eq.getTimestamp())
                        .externalId(eq.getExternalId())
                        .place(eq.getPlace())
                        .intensidade(intensidade)
                        .nivel(nivel)
                        .build();
            })
            .collect(Collectors.toList());
    }

    private List<User> encontrarUsuariosProximos(double lat, double lon) {
        return userRepository.findAll().stream()
                .filter(user -> GeoUtils.calcularDistanciaKm(lat, lon, user.getLatitude(), user.getLongitude()) <= ALERT_RADIUS_KM)
                .collect(Collectors.toList());
    }

    private EarthquakeEvent fromRequestDto(EarthquakeEventRequestDTO dto) {
        return EarthquakeEvent.builder()
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .depth(dto.getDepth())
                .magnitude(dto.getMagnitude())
                .timestamp(dto.getTimestamp())
                .place(dto.getPlace())
                .build();
    }

    private EarthquakeEventResponseDTO toResponseDto(EarthquakeEvent eq) {
        return EarthquakeEventResponseDTO.builder()
                .id(eq.getId())
                .externalId(eq.getExternalId())
                .place(eq.getPlace())
                .latitude(eq.getLatitude())
                .longitude(eq.getLongitude())
                .magnitude(eq.getMagnitude())
                .depth(eq.getDepth())
                .timestamp(eq.getTimestamp())
                .build();
    }
}