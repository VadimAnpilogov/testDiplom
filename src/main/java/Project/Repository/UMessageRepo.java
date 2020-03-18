package Project.Repository;


import Project.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UMessageRepo extends CrudRepository<User, Long> {
    List<User> findAllByOrderByIdAsc();
    List<User> findAllByRl(int rl);
//    List<User> findByUsername(String username);
}
