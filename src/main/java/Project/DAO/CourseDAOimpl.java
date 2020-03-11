package Project.DAO;


import Project.Entity.Course;
import org.hibernate.Session;

import java.util.List;

public class CourseDAOimpl implements CourseDAO{

    private Session session;

    @Override
    public List<Course> findAll() {
        return session.createQuery("from Course c").list();
    }

    @Override
    public List<Course> findAllWithDetail() {
        return null;
    }

    @Override
    public Course findById(Long id) {
        return null;
    }

    @Override
    public Course save(Course course) {
        return null;
    }

    @Override
    public void delete(Course course) {

    }
    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
