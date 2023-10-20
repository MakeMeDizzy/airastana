package airastana.test.task.repository;

import airastana.test.task.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM public.user WHERE username = :userName")
    Optional<User> findUserByUsername(String userName);
}
