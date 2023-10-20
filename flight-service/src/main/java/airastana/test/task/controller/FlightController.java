package airastana.test.task.controller;

import airastana.test.task.dto.FlightDTO;
import airastana.test.task.dto.FlightResponseDTO;
import airastana.test.task.dto.FlightStatusUpdateRequestDTO;
import airastana.test.task.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    @Operation(summary = "Get all flights")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('MODERATOR', 'USER')")
    public ResponseEntity<List<FlightResponseDTO>> getFlights(
            @RequestParam(required = false) String origin,
            @RequestParam(required = false) String destination
    ){
        return ResponseEntity.ok(flightService.getAllFlights(origin, destination));
    }

    @Operation(summary = "Add new flight")
    @PostMapping
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ResponseEntity<Void> addFlight(@Valid @RequestBody FlightDTO dto) {
        flightService.addFlight(dto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Change status of flight")
    @PatchMapping("/{flightId}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ResponseEntity<Void> changeStatusOfFlight(@Valid @RequestBody FlightStatusUpdateRequestDTO dto, @PathVariable Long flightId){
        flightService.changeStatus(dto.getStatus(), flightId);
        return ResponseEntity.noContent().build();
    }



}
