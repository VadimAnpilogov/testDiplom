package Project.Repository;


import Project.Entity.Course;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends CrudRepository<Course, Long > {
    List<Course> findAllByOrderByIdAsc();
//    List<Course> findByCourseName(String courseName);
    Course findByCourseName(String courseName);
    List<Course> findByCourseName(Iterable courseName);;
}
