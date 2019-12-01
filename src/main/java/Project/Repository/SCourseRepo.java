package Project.Repository;



import Project.Entity.SignUpCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SCourseRepo extends CrudRepository<SignUpCourse, Long > {
//    Course findByCourseName(String courseName);

}
