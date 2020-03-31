package Project.Repository;


import Project.Entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends CrudRepository<Course, Long > {
    List<Course> findAllByOrderByIdAsc();
    List<Course> findByCourseNameOrderByIdAsc(String CourseName);
    Course findByCourseName(String courseName);


}
