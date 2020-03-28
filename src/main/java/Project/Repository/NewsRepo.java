package Project.Repository;

import Project.Entity.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepo extends CrudRepository<News, Long> {
    List<News> findByAuthorNewsOrderByDateDesc(String AuthorNews);
}
