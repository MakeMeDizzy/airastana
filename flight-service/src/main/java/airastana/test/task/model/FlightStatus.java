package airastana.test.task.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FlightStatus {

    InTime("InTime"),
    Delayed("Delayed"),
    Cancelled("Cancelled");

    private final String status;

    FlightStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }

    @JsonCreator
    public static FlightStatus fromStatus(String status) {
        for (FlightStatus flightStatus : values()) {
            if (flightStatus.status.equalsIgnoreCase(status)) {
                return flightStatus;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }
}
