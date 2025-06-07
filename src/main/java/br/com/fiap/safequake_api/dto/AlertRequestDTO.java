package br.com.fiap.safequake_api.dto;

import lombok.Data;

@Data
public class AlertRequestDTO {
    private double latitude;
    private double longitude;
}