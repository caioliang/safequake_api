package br.com.fiap.safequake_api.service;

import br.com.fiap.safequake_api.dto.EarthquakeEventFullResponseDTO;
import br.com.fiap.safequake_api.dto.EarthquakeEventRequestDTO;
import br.com.fiap.safequake_api.dto.EarthquakeEventResponseDTO;
import br.com.fiap.safequake_api.model.EarthquakeEvent;
import br.com.fiap.safequake_api.model.User;
import br.com.fiap.safequake_api.repository.EarthquakeEventRepository;
import br.com.fiap.safequake_api.repository.UserRepository;
import br.com.fiap.safequake_api.util.GeoUtils;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Cadastro de abalos sísmicos")
    @Transactional
    public EarthquakeEventResponseDTO createManual(EarthquakeEventRequestDTO dto) {
        EarthquakeEvent event = fromRequestDto(dto);
        event.setExternalId("manual_" + UUID.randomUUID());
        EarthquakeEvent saved = earthquakeRepository.save(event);

        return toResponseDto(saved);
    }

    public List<EarthquakeEventFullResponseDTO> buscarAlertasProximos(double latitude, double longitude) {
        return earthquakeRepository.findAll().stream()
                .filter(eq -> {
                    String nivel = GeoUtils.definirNivel(eq.getMagnitude());
                    double distancia = GeoUtils.calcularDistanciaKm(latitude, longitude, eq.getLatitude(), eq.getLongitude());
                    return distancia <= 70 && (nivel.equals("ALTO") || nivel.equals("CRÍTICO"));
                })
                .map(eq -> EarthquakeEventFullResponseDTO.builder()
                        .id(eq.getId())
                        .latitude(eq.getLatitude())
                        .longitude(eq.getLongitude())
                        .magnitude(eq.getMagnitude())
                        .timestamp(eq.getTimestamp())
                        .externalId(eq.getExternalId())
                        .place(eq.getPlace())
                        .nivel(GeoUtils.definirNivel(eq.getMagnitude()))
                        .build())
                .collect(Collectors.toList());
    }

    public List<EarthquakeEventResponseDTO> findAll() {
        return earthquakeRepository.findAll().stream()
            .map(this::toResponseDto)
            .collect(Collectors.toList());
    }

    public List<EarthquakeEventFullResponseDTO> findAllWithClassification() {
    return earthquakeRepository.findAll().stream()
            .map(eq -> {
            String nivel = GeoUtils.definirNivel(eq.getMagnitude());
                return EarthquakeEventFullResponseDTO.builder()
                        .id(eq.getId())
                        .latitude(eq.getLatitude())
                        .longitude(eq.getLongitude())
                        .magnitude(eq.getMagnitude())
                        .timestamp(eq.getTimestamp())
                        .externalId(eq.getExternalId())
                        .place(eq.getPlace())
                        .nivel(nivel)
                        .build();
            })
            .collect(Collectors.toList());
    }


    private EarthquakeEvent fromRequestDto(EarthquakeEventRequestDTO dto) {
        return EarthquakeEvent.builder()
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
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
                .timestamp(eq.getTimestamp())
                .build();
    }
}