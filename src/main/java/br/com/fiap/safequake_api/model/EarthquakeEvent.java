package br.com.fiap.safequake_api.model;

import jakarta.persistence.*;
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

    private Double latitude;

    private Double longitude;

    private Double magnitude;

    private LocalDateTime timestamp;

    @Column(unique = true)
    private String externalId;

    private String place;
}