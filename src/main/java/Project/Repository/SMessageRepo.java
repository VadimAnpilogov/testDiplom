package Project.Repository;


import Project.Entity.Messages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SMessageRepo extends CrudRepository<Messages, Integer> {
    List<Messages> findByRecipientAndSender(String recipient, String sender);


}