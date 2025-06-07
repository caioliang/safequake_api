package br.com.fiap.safequake_api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EarthquakeEventFullResponseDTO {
    private Long id;
    private Double latitude;
    private Double longitude;
    private Double magnitude;
    private LocalDateTime timestamp;
    private String externalId;
    private String place;
    private String nivel;
}