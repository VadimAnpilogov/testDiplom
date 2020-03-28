package Project.Repository;


import Project.Entity.Theme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepo extends CrudRepository <Theme, Long> {
    List <Theme> findAllByOrderByDateAsc();
    List <Theme> findByNameCourseOrderByDateAsc(String nameCourse);
    Theme findById(int id);
}
