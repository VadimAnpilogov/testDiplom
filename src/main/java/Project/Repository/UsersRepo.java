package Project.Repository;


import Project.Entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends CrudRepository<Users, Long> {
    Users findByUsername(String username);

}
