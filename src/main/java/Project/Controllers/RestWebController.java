package Project.Controllers;

import Project.Entity.*;
import Project.Repository.*;
import Project.Service.MessageService;
import Project.Service.UserService;
import Project.message.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public UMessageRepo uMessageRepo;
	@Autowired
	private DialogRepo dialogRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UsersListRepo usersListRepo;
	@Autowired
	private MessagesController messagesController;

	public boolean statusMessage = false;

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
	public Response getHelp(){
		Iterable<Chat> helpS = chatRepo.findAllByOrderByIdAsc();
		Response response = new Response("Done" , helpS );
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
	private String recipient1="test";
	private String recipient2="test";
	public String NameMess= "test";

	@GetMapping(value = "message/all")
	public Response getMessage(
			@AuthenticationPrincipal Users user){

//		Iterable<Dialog> dialogs = dialogRepo.findAllBySenderOrderByIdAsc(user.getUsername());
		recipient1=messagesController.recipient1;
//		if(recipient1 == recipient2){
//			Response response = new Response("Done" ,dialogs);
//			return response;
//		}

		List<Messages> messages2 = sMessageRepo.findByNameMessOrderByDateAsc(messageService.nameDialog(recipient1, user.getUsername()));
		for (int i = 0; i< messages2.size(); i++){
			if(messages2.get(i).getRecipient().equals(user.getUsername())){
				messages2.get(i).setStatusMessage(true);
				sMessageRepo.save(messages2.get(i));
			}

		}

		Response response = new Response("Done" , messages2 );

		return response;
	}
	public String nameMessD;
	@PostMapping(value = "message/save")
	public Response postMessage(
			@AuthenticationPrincipal Users user,
			@RequestBody Messages message
	){
		recipient1=messagesController.recipient1;
		Messages messages = new Messages(
				message.getMessage(),
				recipient1,
				user.getUsername(),
				messageService.nameDialog(recipient1, user.getUsername()),
				userService.date(),
				statusMessage
				);
//		nameMessD = messageService.nameDialog(recipient1, user.getUsername());
		List<Dialog> dialog = dialogRepo.findByNameMess(messageService.nameDialog(recipient1, user.getUsername()));
		sMessageRepo.save(messages);
		dialog.get(0).getMessageDialogs().add(messages);
		dialogRepo.save(dialog.get(0));
		dialog.get(1).getMessageDialogs().add(messages);

		dialogRepo.save(dialog.get(1));
		Response response = new Response("Done", message);
		return response;
	}


	@GetMapping(value = "users/all")
	public Response getUsers(
			@AuthenticationPrincipal Users user
	){
		Iterable<Users> users = usersListRepo.findAll();
		Response response = new Response("Done" , users );
		return response;
	}
}