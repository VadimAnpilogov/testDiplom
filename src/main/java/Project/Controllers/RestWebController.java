package Project.Controllers;

import Project.Entity.*;
import Project.Repository.*;
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
	@Autowired
	private DialogRepo dialogRepo;

	@GetMapping(value = "rewiews/all")
	public Response getReview() {
		Iterable<Reviews> custom = reviewsRepo.findAllByOrderByIdAsc();
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

	@GetMapping(value = "messageU/{recipient}/all")
	public Response getMessage(
			@AuthenticationPrincipal User user,
			@PathVariable String recipient){

		Iterable<Dialog> dialogs = dialogRepo.findAllBySenderOrderByIdAsc(user.getUsername());

		Iterable<Messages> messages2 = sMessageRepo.findByNameMessOrderByDateAsc(messageService.nameDialog(recipient, user.getUsername()));
		Response response = new Response("Done" ,dialogs, messages2 );
		return response;
	}

	@PostMapping(value = "messageU/{recipient}/save")
	public Response postMessage(
			@AuthenticationPrincipal User user,
			@RequestBody Messages message,
			@PathVariable String recipient
	){

		Messages messages = new Messages(
				message.getMessage(),
				recipient,
				user.getUsername(),
				messageService.nameDialog(recipient, user.getUsername()),
				userSevice.date());

		sMessageRepo.save(messages);

		Response response = new Response("Done", message);
		return response;
	}
	@GetMapping(value = "message/all")
	public Response getDialog(@AuthenticationPrincipal User user){
		Iterable<Dialog> dialogs = dialogRepo.findAllBySenderOrderByIdAsc(user.getUsername());
		Response response = new Response("Done" , dialogs );
		return response;
	}
}