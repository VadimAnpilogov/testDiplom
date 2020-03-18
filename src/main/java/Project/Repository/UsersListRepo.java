package Project.Repository;

import Project.Entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersListRepo extends CrudRepository<Users, Long> {
    List<Users> findByUsername(String username);
    List<Users> findAllByOrderByIdAsc();
}
