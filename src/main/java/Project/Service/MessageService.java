package Project.Service;

import Project.Entity.Messages;
import Project.Repository.SMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private String NameMess;
    public String sen;

    @Autowired
    public SMessageRepo sMessageRepo;

    public String nameDialog(String recipient, String sender){

        NameMess=recipient+sender;
        Iterable<Messages> messages1 = sMessageRepo.findByNameMess(NameMess);
        sen=messages1.toString();
        if (sen == "[]") {
            NameMess = sender+recipient;
        }
        else {
            NameMess =recipient+sender;
        }

        return NameMess;
    }

}
