package airastana.test.task.mapper;

import airastana.test.task.dto.FlightResponseDTO;
import airastana.test.task.model.Flight;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    FlightResponseDTO flightToFlightResponseDTO(Flight flight);

}