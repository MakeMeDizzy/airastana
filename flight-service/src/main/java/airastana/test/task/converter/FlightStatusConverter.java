package airastana.test.task.converter;

import airastana.test.task.model.FlightStatus;
import org.springframework.stereotype.Component;

@Component
public class FlightStatusConverter {
    public static FlightStatus fromString(String statusString) {
        if (statusString != null) {
            switch (statusString) {
                case "InTime":
                    return FlightStatus.InTime;
                case "Delayed":
                    return FlightStatus.Delayed;
                case "Cancelled":
                    return FlightStatus.Cancelled;
                default:
                    throw new IllegalArgumentException("Invalid Flight Status " + statusString);
            }
        }
        return null;
    }
}
