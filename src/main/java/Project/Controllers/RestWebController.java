package Project.Controllers;

import Project.Entity.*;
import Project.Repository.*;
import Project.Service.MessageService;
import Project.Service.UserService;
import Project.message.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


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


	@GetMapping(value = "messageU/{recipient}/all")
	public Response getMessageU(
			@AuthenticationPrincipal Users user,
			@PathVariable String recipient){
		recipient1 = recipient;
		Iterable<Dialog> dialogs = dialogRepo.findAllBySenderOrderByIdAsc(user.getUsername());

//		Iterable<Messages> messages2 = sMessageRepo.findByNameMessOrderByDateAsc(messageService.nameDialog(recipient, user.getUsername()));
		Response response = new Response("Done" ,dialogs );
		return response;
	}

	@GetMapping(value = "message/all")
	public Response getMessage(
			@AuthenticationPrincipal Users user){

		Iterable<Dialog> dialogs = dialogRepo.findAllBySenderOrderByIdAsc(user.getUsername());
		recipient1=messagesController.recipient1;
		if(recipient1 == recipient2){
			Response response = new Response("Done" ,dialogs);
			return response;
		}

		Iterable<Messages> messages2 = sMessageRepo.findByNameMessOrderByDateAsc(messageService.nameDialog(recipient1, user.getUsername()));
		Response response = new Response("Done" ,dialogs, messages2 );

		return response;
	}
	@PostMapping(value = "messageU/{recipient}/save")
	public Response postMessage(
			@AuthenticationPrincipal Users user,
			@RequestBody Messages message,
			@PathVariable String recipient
	){

		Messages messages = new Messages(
				message.getMessage(),
				recipient,
				user.getUsername(),
				messageService.nameDialog(recipient, user.getUsername()),
				userService.date());

		sMessageRepo.save(messages);

		Response response = new Response("Done", message);
		return response;
	}
//	@GetMapping(value = "message/all")
//	public Response getDialog(@AuthenticationPrincipal Users user){
//		Iterable<Dialog> dialogs = dialogRepo.findAllBySenderOrderByIdAsc(user.getUsername());
//		Response response = new Response("Done" , dialogs );
//		return response;
//	}

	@GetMapping(value = "users/all")
	public Response getUsers(
			@AuthenticationPrincipal Users user
	){
		Iterable<Users> users = usersListRepo.findAll();
		Response response = new Response("Done" , users );
		return response;
	}
}