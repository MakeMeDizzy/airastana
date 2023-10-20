import airastana.test.task.dto.FlightDTO;
import airastana.test.task.dto.FlightResponseDTO;
import airastana.test.task.mapper.FlightMapper;
import airastana.test.task.model.Flight;
import airastana.test.task.model.FlightStatus;
import airastana.test.task.repository.FlightRepository;
import airastana.test.task.service.impl.FlightServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    @Mock
    private FlightMapper flightMapper;




    private Flight testFlight;

    @BeforeEach
    public void setUp() {
        testFlight = new Flight();
        testFlight.setId(1L);
        testFlight.setOrigin("Origin");
        testFlight.setDestination("Destination");
        testFlight.setDeparture(OffsetDateTime.now());
        testFlight.setArrival(OffsetDateTime.now().plusHours(2));
        testFlight.setStatus(FlightStatus.InTime.getStatus());
    }

    @Test
    public void getAllFlightsTest() {

        FlightResponseDTO mockResponse = new FlightResponseDTO();
        mockResponse.setOrigin("Origin");
        mockResponse.setDestination("Destination");
        mockResponse.setDeparture(OffsetDateTime.now());
        mockResponse.setArrival(OffsetDateTime.now().plusHours(2));
        mockResponse.setStatus(FlightStatus.InTime);

        when(flightMapper.flightToFlightResponseDTO(any(Flight.class))).thenReturn(mockResponse);

        when(flightRepository.findFlights(anyString(), anyString())).thenReturn(Collections.singletonList(testFlight));

        List<FlightResponseDTO> flights = flightService.getAllFlights("Origin", "Destination");

        assertThat(flights).isNotNull();
        assertThat(flights.size()).isEqualTo(1);
        verify(flightRepository, times(1)).findFlights("Origin", "Destination");
    }

    @Test
    public void addFlightTest() {
        FlightDTO newFlight = new FlightDTO();
        newFlight.setOrigin("NewOrigin");
        newFlight.setDestination("NewDestination");
        newFlight.setDeparture(OffsetDateTime.now());
        newFlight.setArrival(OffsetDateTime.now().plusHours(3));
        newFlight.setFlightStatus(FlightStatus.InTime);

        when(flightRepository.save(any(Flight.class))).thenReturn(testFlight);

        flightService.addFlight(newFlight);

        verify(flightRepository, times(1)).save(any(Flight.class));
    }

    @Test
    public void changeStatusTest() {
        when(flightRepository.findById(1L)).thenReturn(Optional.of(testFlight));

        flightService.changeStatus(FlightStatus.Delayed, 1L);

        assertThat(testFlight.getStatus()).isEqualTo(FlightStatus.Delayed.getStatus());
        verify(flightRepository, times(1)).findById(1L);
        verify(flightRepository, times(1)).save(testFlight);
    }

    @Test
    public void changeStatusFlightNotFoundTest() {
        when(flightRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> flightService.changeStatus(FlightStatus.Delayed, 1L),
                "Expected changeStatus to throw, but it didn't"
        );

        assertThat(thrown.getMessage()).isEqualTo("Flight not found with ID: " + 1L);
        verify(flightRepository, times(1)).findById(1L);
        verify(flightRepository, times(0)).save(any(Flight.class));
    }
}

