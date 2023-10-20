package airastana.test.task.dto;

import airastana.test.task.model.FlightStatus;
import airastana.test.task.validator.FlightStatusSubset;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FlightStatusUpdateRequestDTO {

    @NotNull(message = "Flight status cannot be null")
    @FlightStatusSubset(anyOf = { FlightStatus.InTime, FlightStatus.Delayed, FlightStatus.Cancelled })
    private FlightStatus status;
}
