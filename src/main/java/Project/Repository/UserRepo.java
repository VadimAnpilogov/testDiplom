package Project.Repository;


import Project.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepo extends CrudRepository<User, Long> {
//    User findByUsername(String username);
    User findByActivationCode(String code);

//    Optional<User> findByIdNew(String idNew);

//    @Query("select usr.username from usr, user_role where user_role.roles=:roles and usr.id=user_role.user_id")
//    List<User> findAllByUsername(@Param("roles") User user);

}