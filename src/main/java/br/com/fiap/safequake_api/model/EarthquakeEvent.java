package br.com.fiap.safequake_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "earthquake_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EarthquakeEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Latitude é obrigatória")
    @DecimalMin(value = "-90.0", message = "Latitude mínima é -90")
    @DecimalMax(value = "90.0", message = "Latitude máxima é 90")
    private Double latitude;

    @NotNull(message = "Longitude é obrigatória")
    @DecimalMin(value = "-180.0", message = "Longitude mínima é -180")
    @DecimalMax(value = "180.0", message = "Longitude máxima é 180")
    private Double longitude;

    @NotNull(message = "Magnitude é obrigatória")
    @DecimalMin(value = "0.0", inclusive = false, message = "Magnitude deve ser maior que zero")
    @DecimalMax(value = "10.0", message = "Magnitude máxima é 10")
    private Double magnitude;

    @NotNull(message = "Profundidade é obrigatória")
    @DecimalMin(value = "0.0", message = "Profundidade mínima é 0")
    private Double depth;

    @NotNull(message = "Timestamp é obrigatório")
    private LocalDateTime timestamp;

    @Column(unique = true)
    private String externalId;

    private String place;
}