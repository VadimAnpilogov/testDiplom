package Project.Controllers;


import Project.Entity.Dialog;
import Project.Entity.Users;
import Project.Repository.DialogRepo;
import Project.Repository.SMessageRepo;
import Project.Repository.UsersListRepo;
import Project.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class MessagesController {

    public String recipient1 = "test";
    public String NameMess= "test";
    public String date;
    public String namePage = "Чат";

    @Autowired
    public SMessageRepo sMessageRepo;
    @Autowired
    private MessageService messageService;
    @Autowired
    private DialogRepo dialogRepo;
    @Autowired
    private UsersListRepo usersListRepo;

//Страница сообщений
    @GetMapping("message")
    public String message(
            @AuthenticationPrincipal Users user,
            Model model){
        Iterable<Users> messU = usersListRepo.findAllByOrderByIdAsc();
        model.addAttribute("messageU", messU);

        Iterable<Dialog> dialogs = dialogRepo.findAllBySenderOrderByIdAsc(user.getUsername());
        model.addAttribute("dialogsUser", dialogs);
        model.addAttribute("colorDialog", recipient1);

        return "message";
    }
    @GetMapping("messageDown")
    public String messageDown(
            @AuthenticationPrincipal Users user,
            Model model){
        recipient1 = "test";
        Iterable<Users> messU = usersListRepo.findAllByOrderByIdAsc();
        model.addAttribute("messageU", messU);

        Iterable<Dialog> dialogs = dialogRepo.findAllBySenderOrderByIdAsc(user.getUsername());
        model.addAttribute("dialogsUser", dialogs);

        return "message";
    }

    //Получение имени Получателя
    @GetMapping("/messageU/{recipient}")
    public String messageU(
            @AuthenticationPrincipal Users user,
            @PathVariable String recipient, Model model){
        model.addAttribute("test", "test");
        recipient1 = recipient;

        Iterable<Users> messU = usersListRepo.findAllByOrderByIdAsc();
        model.addAttribute("messageU", messU);
        NameMess = messageService.nameDialog(recipient1,user.getUsername() );

        Iterable<Dialog> dialogs = dialogRepo.findAllBySenderOrderByIdAsc(user.getUsername());
        model.addAttribute("dialogsUser", dialogs);
        return "redirect:/message";
    }

    //удаление диалога
    @GetMapping("/deleteDialog/{namemess}")
    public String DeleteDialogs(
            @PathVariable String namemess){
        messageService.deleteDialog(namemess);

        return "redirect:/messageDown";
    }

//создание диалога
    @GetMapping("/messageUsers/{recipient}")
    public String messageUsers(
            @AuthenticationPrincipal Users user,
            @PathVariable String recipient){
        messageService.createDialog(user.getUsername(), recipient);
        return "redirect:/messageU/{recipient}";
    }



}
