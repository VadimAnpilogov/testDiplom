package Project.Service;

import Project.Entity.Dialog;
import Project.Entity.Messages;
import Project.Repository.DialogRepo;
import Project.Repository.SMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private String NameMess;
    public String sen;

    @Autowired
    public SMessageRepo sMessageRepo;
    @Autowired
    private MessageService messageService;
    @Autowired
    private DialogRepo dialogRepo;

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

    public void createDialog(String userName1, String userName) {
        String namemess = nameDialog(userName, userName1);

        List<Dialog> dialogs1 = dialogRepo.findByNameMess(namemess);
        if(dialogs1.size() == 0){
            Dialog dialog = new Dialog(namemess, userName1, userName);
            dialogRepo.save(dialog);
            Dialog dialog2 = new Dialog(namemess, userName, userName1);
            dialogRepo.save(dialog2);
        }

    }

    public void deleteDialog(Long id){

        Optional<Dialog> dialog = dialogRepo.findById(id);
        dialogRepo.deleteById(id);
        List<Dialog> dialog1 = dialogRepo.findByNameMess(dialog.get().getNameMess());
        dialogRepo.deleteById(dialog1.get(0).getId());

    }
}
