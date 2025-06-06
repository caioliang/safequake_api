package br.com.fiap.safequake_api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EarthquakeEventResponseDTO {

    private Long id;
    private String externalId;
    private String place;
    private Double latitude;
    private Double longitude;
    private Double magnitude;
    private LocalDateTime timestamp;
}