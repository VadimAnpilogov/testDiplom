package Project.Repository;


import Project.Entity.Reviews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepo extends CrudRepository<Reviews, Long> {
    List<Reviews> findByUserReviews(String UserReviews);
    List<Reviews> findAllByOrderByIdAsc();

}
