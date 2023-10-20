package airastana.test.task.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;

@Data
@Table("flight")
public class Flight {
    @Id
    private Long id;
    private String origin;
    private String destination;
    private OffsetDateTime departure;
    private OffsetDateTime arrival;
    private String status;

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", status='" + status + '\'' +
                '}';
    }
}
