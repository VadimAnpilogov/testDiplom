package Project.Repository;


import Project.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UMessageRepo extends CrudRepository<User, Integer> {
    List<User> findAllByOrderByIdAsc();

}
