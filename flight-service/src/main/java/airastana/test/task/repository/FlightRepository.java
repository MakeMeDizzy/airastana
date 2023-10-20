package airastana.test.task.repository;

import airastana.test.task.model.Flight;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
    @Query("SELECT * FROM Flight f WHERE (:origin IS NULL OR f.origin = :origin) AND (:destination IS NULL OR f.destination = :destination)")
    List<Flight> findFlights(@Param("origin") String origin, @Param("destination") String destination);
}
