package Project.Controllers;

import Project.Entity.*;
import Project.Repository.*;
import Project.Service.MailSender;
import Project.Service.MessageService;
import Project.Service.UserService;
import Project.message.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class RestWebController {

	@Autowired
	public ReviewsRepo reviewsRepo;
	@Autowired
	private UserService userService;
	@Autowired
	private ChatRepo chatRepo;
	@Autowired
	public SMessageRepo sMessageRepo;
	@Autowired
	private MessageService messageService;
	@Autowired
	private DialogRepo dialogRepo;
	@Autowired
	private UsersListRepo usersListRepo;
	@Autowired
	private MessagesController messagesController;
	@Autowired
	private MailSender mailSender;

	public boolean statusMessage = false;
	private String recipient1="test";
	public int count1 = 0;

	@GetMapping(value = "rewiews/all")
	public Response getReview() {
		Iterable<Reviews> custom = reviewsRepo.findAllByOrderByIdAsc();
		Response response = new Response("Done", custom);
		return response;
	}
	@PostMapping(value = "rewiews/save")
	public Response postReview(
			@AuthenticationPrincipal Users user,
			@RequestBody Reviews customer) {
		Reviews reviews = new Reviews(customer.getReviewsOp(), user.getUsername(), userService.date());
		reviewsRepo.save(reviews);
		Response response = new Response("Done", customer);
		return response;
	}

	@GetMapping(value = "help/all")
	public Response getHelp(@AuthenticationPrincipal Users users){
		Iterable<Chat> helpS = chatRepo.findAllByOrderByIdAsc();
		count1 =0;
		MessageCount(users.getUsername());
		Response response = new Response("Done" , helpS, count1 );
		return response;
	}
	@PostMapping(value = "help/save")
	public Response postHelp(
			@AuthenticationPrincipal Users user,
			@RequestBody Chat chat
			){
		Chat chats = new Chat(chat.getMessage(), user.getUsername(), userService.date());
		chatRepo.save(chats);

		Response response = new Response("Done", chat);
		return response;
	}


	@GetMapping(value = "message={recipient}/all")
	public Response getMessage(
			@AuthenticationPrincipal Users user,
			@PathVariable String recipient){

		recipient1=messagesController.recipient1;

		List<Messages> messages2 = sMessageRepo.findByNameMessOrderByDateAsc(messageService.nameDialog(recipient, user.getUsername()));
		for (int i = 0; i< messages2.size(); i++){
			if(messages2.get(i).getRecipient().equals(user.getUsername())){
				messages2.get(i).setStatusMessage(true);
				sMessageRepo.save(messages2.get(i));
			}

		}
		count1 =0;
		MessageCount(user.getUsername());
		Object sender = MessageDialog(user.getUsername());
		Response response = new Response("Done" , messages2, count1, sender);
		return response;
	}
	@PostMapping(value = "message={recipient}/save")
	public Response postMessage(
			@AuthenticationPrincipal Users user,
			@RequestBody Messages message,
			@PathVariable String recipient
	){
		recipient1=messagesController.recipient1;
		Messages messages = new Messages(
				message.getMessage(),
				recipient1,
				user.getUsername(),
				messageService.nameDialog(recipient, user.getUsername()),
				userService.date(),
				statusMessage
				);
		List<Dialog> dialog = dialogRepo.findByNameMess(messageService.nameDialog(recipient, user.getUsername()));
		sMessageRepo.save(messages);
		dialog.get(0).getMessageDialogs().add(messages);
		dialogRepo.save(dialog.get(0));
		dialog.get(1).getMessageDialogs().add(messages);

		dialogRepo.save(dialog.get(1));
		Response response = new Response("Done", message);
		return response;
	}

	@GetMapping(value = "users/all")
	public Response getUsers(@AuthenticationPrincipal Users users){
		List<Users> users1 = usersListRepo.findAllByOrderByIdAsc();
		count1 =0;
		MessageCount(users.getUsername());
		Response response = new Response("Done" , users1, count1);
		return response;
	}

	@PostMapping(value = "contacts/messageDev")
	public Response messageDev(
			@AuthenticationPrincipal Users users,
			@RequestBody Chat chat){

		String message1 = String.format(
				"Сообщение от %s! \n" +
						"%s",
				users.getUsername(),
				chat.getMessage()
		);
		mailSender.send("vadick.anpilogov2015@yandex.ru", "Сообщения для разработчиков", message1);
		mailSender.send("denis.moroz.98@gmail.com", "Сообщения для разработчиков", message1);
		Response response = new Response("Done" , "Готово");
		return response;
	}


	public void MessageCount(String recipient){
		List<Messages> messages = sMessageRepo.findByRecipient(recipient);
		for(int i = 0; i< messages.size(); i++){
			if(messages.get(i).isStatusMessage() == false){
				count1++;
			}
		}
	}

	@GetMapping(value = {"Personal/count", "course/count", "myCourse/count","createCourse/count", "contacts/count", "theme/count", "PersonalData/count", "message/count"})
	public Response Personal(@AuthenticationPrincipal Users users){
		count1 =0;
		MessageCount(users.getUsername());
		Response response = new Response("DoneYes", count1);
		return response;
	}

	@GetMapping(value = {"SCourse={id}/count"})
	public Response Scourse(@AuthenticationPrincipal Users users,
							 @PathVariable int id){
		count1 =0;
		MessageCount(users.getUsername());
		Response response = new Response("DoneYes", count1);
		return response;
	}

	@GetMapping(value = {"userPers={users}/count"})
	public Response Users(@AuthenticationPrincipal Users user,
							@PathVariable String users){
		count1 =0;
		MessageCount(user.getUsername());
		Response response = new Response("DoneYes", count1);
		return response;
	}


	public Object MessageDialog(String recipient){
		ArrayList<String> sender = new ArrayList<>();
		List<Messages> messages = sMessageRepo.findByRecipient(recipient);
		for(int i = 0; i< messages.size(); i++){
			if(messages.get(i).isStatusMessage() == false){
				sender.add(messages.get(i).getSender());
			}
		}
		return  sender;
	}

	@GetMapping("message/countD")
	public Response DialogsMess(@AuthenticationPrincipal Users users){
		Object sender = MessageDialog(users.getUsername());
		Response response = new Response("DoneYes", sender);
		return response;
	}

}