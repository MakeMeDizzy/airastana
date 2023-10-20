package airastana.test.task.service.impl;

import airastana.test.task.dto.FlightDTO;
import airastana.test.task.dto.FlightResponseDTO;
import airastana.test.task.exception.FlightNotFoundException;
import airastana.test.task.mapper.FlightMapper;
import airastana.test.task.model.Flight;
import airastana.test.task.model.FlightStatus;
import airastana.test.task.repository.FlightRepository;
import airastana.test.task.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Override
    @Cacheable(value = "flightCache", key = "#origin + '_' + #destination")
    public List<FlightResponseDTO> getAllFlights(String origin, String destination) {
        List<Flight> flights = flightRepository.findFlights(origin, destination);
        return flights.stream()
                .map(flightMapper::flightToFlightResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @CacheEvict(value = "flightCache", allEntries = true)
    public void addFlight(FlightDTO dto) {
        Flight flight = new Flight();
        flight.setOrigin(dto.getOrigin());
        flight.setDestination(dto.getDestination());
        flight.setDeparture(dto.getDeparture());
        flight.setArrival(dto.getArrival());
        flight.setStatus(dto.getFlightStatus().getStatus());
        flightRepository.save(flight);
        log.info("flight : {} successfully added", flight);
    }

    @Override
    @Transactional
    @CacheEvict(value = "flightCache", allEntries = true)
    public void changeStatus(FlightStatus flightStatus, Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException(flightId));
        flight.setStatus(flightStatus.getStatus());
        flightRepository.save(flight);
        log.info("Flight status changed for flightId = {}", flightId);
    }
}
