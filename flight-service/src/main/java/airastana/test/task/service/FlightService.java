package airastana.test.task.service;

import airastana.test.task.dto.FlightDTO;
import airastana.test.task.dto.FlightResponseDTO;
import airastana.test.task.model.FlightStatus;

import java.util.List;

public interface FlightService {
    List<FlightResponseDTO> getAllFlights(String origin, String destination);
    void addFlight(FlightDTO dto);
    void changeStatus(FlightStatus flightStatus, Long flightId);
}
