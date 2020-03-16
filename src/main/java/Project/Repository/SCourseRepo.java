package Project.Repository;



import Project.Entity.SignUpCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SCourseRepo extends CrudRepository<SignUpCourse, Long > {
//    Course findByCourseName(String courseName);
        List<SignUpCourse> findByCourseName(String CourseName);
//        List<SignUpCourse> findByUserCourseName(String UserCourseName);

}
