package Project.Controllers;

import Project.Entity.Chat;
import Project.Entity.Messages;
import Project.Entity.Reviews;
import Project.Entity.User;
import Project.Repository.ChatRepo;
import Project.Repository.ReviewsRepo;
import Project.Repository.SMessageRepo;
import Project.Repository.UMessageRepo;
import Project.Service.MessageService;
import Project.Service.UserSevice;
import Project.message.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
public class RestWebController {

	@Autowired
	public ReviewsRepo reviewsRepo;
	@Autowired
	private UserSevice userSevice;
	@Autowired
	private ChatRepo chatRepo;
	@Autowired
	public SMessageRepo sMessageRepo;
	@Autowired
	private MessageService messageService;
	@Autowired
	public UMessageRepo uMessageRepo;

	@GetMapping(value = "rewiews/all")
	public Response getReview() {
		Iterable<Reviews> custom = reviewsRepo.findAll();
		Response response = new Response("Done", custom);
		return response;
	}
	@PostMapping(value = "rewiews/save")
	public Response postReview(
			@AuthenticationPrincipal User user,
			@RequestBody Reviews customer) {
		Reviews reviews = new Reviews(customer.getReviewsOp(), user.getUsername(), userSevice.date());
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
			@AuthenticationPrincipal User user,
			@RequestBody Chat chat
			){
		Chat chats = new Chat(chat.getMessage(), user.getUsername(), userSevice.date());
		chatRepo.save(chats);

		Response response = new Response("Done", chat);
		return response;
	}
	public String recipient1 = "test";
	public String NameMess= "test";

	@GetMapping(value = "message/all")
	public Response getMessage(){

//		Iterable<Messages> messages2 = sMessageRepo.findByNameMessOrderByDateAsc(messageService.nameDialog(messages.getRecipient(), user.getUsername()));
		Iterable<Messages> messages2 = sMessageRepo.findAll();
		Response response = new Response("Done" , messages2 );
		return response;
	}

	@PostMapping(value = "message/save")
	public Response postMessage(
			@AuthenticationPrincipal User user,
			@RequestBody Messages message
	){

		Messages messages = new Messages(message.getMessage(), recipient1, user.getUsername(), NameMess, userSevice.date());
		sMessageRepo.save(messages);

		Response response = new Response("Done", message);
		return response;
	}
}