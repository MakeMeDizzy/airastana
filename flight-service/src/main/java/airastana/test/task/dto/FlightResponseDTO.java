package airastana.test.task.dto;

import airastana.test.task.model.FlightStatus;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class FlightResponseDTO {
    private String origin;
    private String destination;
    private OffsetDateTime departure;
    private OffsetDateTime arrival;
    private FlightStatus status;
}
