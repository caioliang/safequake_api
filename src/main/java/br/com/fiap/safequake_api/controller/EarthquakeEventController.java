package br.com.fiap.safequake_api.controller;

import br.com.fiap.safequake_api.dto.EarthquakeEventFullResponseDTO;
import br.com.fiap.safequake_api.dto.EarthquakeEventRequestDTO;
import br.com.fiap.safequake_api.dto.EarthquakeEventResponseDTO;
import br.com.fiap.safequake_api.service.EarthquakeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/earthquakes")
@RequiredArgsConstructor
public class EarthquakeEventController {

    private final EarthquakeService earthquakeService;

    @PostMapping("/manual")
    public ResponseEntity<EarthquakeEventResponseDTO> cadastrarManual(
            @RequestBody @Valid EarthquakeEventRequestDTO dto) {
        
        EarthquakeEventResponseDTO response = earthquakeService.createManual(dto);
        return ResponseEntity.ok(response);
    }

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
}
