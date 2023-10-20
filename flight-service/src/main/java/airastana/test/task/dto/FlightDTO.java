package airastana.test.task.dto;

import airastana.test.task.model.FlightStatus;
import airastana.test.task.validator.FlightStatusSubset;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Data
public class FlightDTO {
    @NotNull(message = "Origin cannot be null")
    @Size(min = 1, max = 25, message = "Origin must be between 1 and 256 characters")
    private String origin;

    @NotNull(message = "Destination cannot be null")
    @Size(min = 1, max = 25, message = "Destination must be between 1 and 256 characters")
    private String destination;

    @NotNull(message = "Departure time cannot be null")
    private OffsetDateTime departure;

    @NotNull(message = "Arrival time cannot be null")
    private OffsetDateTime arrival;

    @NotNull(message = "Flight status cannot be null")
    @FlightStatusSubset(anyOf = { FlightStatus.InTime, FlightStatus.Delayed, FlightStatus.Cancelled })
    private FlightStatus flightStatus;
}
