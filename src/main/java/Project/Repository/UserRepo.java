package Project.Repository;


import Project.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
//    List<User> findAllByRl(int rl);
//    @Query("select usr.username from usr, user_role where user_role.roles=:roles and usr.id=user_role.user_id")
//    List<User> findAllByUsername(@Param("roles") User user);

}