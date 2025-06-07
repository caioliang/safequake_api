package br.com.fiap.safequake_api.controller;

import br.com.fiap.safequake_api.dto.AlertRequestDTO;
import br.com.fiap.safequake_api.dto.EarthquakeEventFullResponseDTO;
import br.com.fiap.safequake_api.dto.EarthquakeEventRequestDTO;
import br.com.fiap.safequake_api.dto.EarthquakeEventResponseDTO;
import br.com.fiap.safequake_api.service.EarthquakeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/earthquakes")
@Tag(name = "Terremotos", description = "Endpoints relacionados a terremotos")
@RequiredArgsConstructor
public class EarthquakeEventController {

    private final EarthquakeService earthquakeService;

    @GetMapping
    public ResponseEntity<List<EarthquakeEventResponseDTO>> listarTodos() {
        List<EarthquakeEventResponseDTO> lista = earthquakeService.findAll();
        return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/classified")
    public ResponseEntity<List<EarthquakeEventFullResponseDTO>> listarClassificados() {
        List<EarthquakeEventFullResponseDTO> lista = earthquakeService.findAllWithClassification();
        return ResponseEntity.ok(lista);
    }
    
    @PostMapping("/manual")
    public ResponseEntity<EarthquakeEventResponseDTO> cadastrarManual(
            @RequestBody @Valid EarthquakeEventRequestDTO dto) {
                
                EarthquakeEventResponseDTO response = earthquakeService.createManual(dto);
                return ResponseEntity.ok(response);
    }

    @PostMapping("/alert")
    public ResponseEntity<List<EarthquakeEventFullResponseDTO>> alert(@RequestBody @Valid AlertRequestDTO dto) {
        List<EarthquakeEventFullResponseDTO> alertas = earthquakeService.buscarAlertasProximos(dto.getLatitude(), dto.getLongitude());
        return ResponseEntity.ok(alertas);
    }
}
