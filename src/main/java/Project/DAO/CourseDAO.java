package Project.DAO;


import Project.Entity.Course;

import java.util.List;

public interface CourseDAO {

    // Найти все контакты.
    public List<Course> findAll();

    // Найти все контакты с заданным телефоном и хобби.
    public List<Course> findAllWithDetail();

    // Найти контакт со всеми деталями по идентификатору.
    public Course findById(Long id);

    // Вставить или обновить контакт.
    public Course save(Course course);

    // Удалить контакт.
    public void delete(Course course);
}
