package br.com.fiap.safequake_api.controller;

import br.com.fiap.safequake_api.dto.AlertRequestDTO;
import br.com.fiap.safequake_api.dto.EarthquakeEventFullResponseDTO;
import br.com.fiap.safequake_api.dto.EarthquakeEventRequestDTO;
import br.com.fiap.safequake_api.dto.EarthquakeEventResponseDTO;
import br.com.fiap.safequake_api.service.EarthquakeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    public ResponseEntity<Page<EarthquakeEventFullResponseDTO>> listarClassificados(
            @RequestParam(required = false) String nivel,
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(earthquakeService.findAllWithClassification(pageable, nivel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EarthquakeEventResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid EarthquakeEventRequestDTO dto) {
        
        EarthquakeEventResponseDTO atualizado = earthquakeService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        earthquakeService.deletar(id);
        return ResponseEntity.noContent().build();
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
