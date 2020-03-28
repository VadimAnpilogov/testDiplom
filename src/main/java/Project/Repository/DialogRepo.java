package Project.Repository;

import Project.Entity.Dialog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DialogRepo extends CrudRepository<Dialog, Long> {
    List<Dialog> findAllByOrderByIdAsc();
    List<Dialog> findAllBySenderOrderByIdAsc(String Sender);
    List<Dialog> findByNameMess(String NameMess);
    Dialog findByNameMessOrderByIdAsc(String NameMess);
}
