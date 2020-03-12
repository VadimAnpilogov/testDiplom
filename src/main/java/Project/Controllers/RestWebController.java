package Project.Controllers;

import Project.Entity.Reviews;
import Project.Entity.User;
import Project.Repository.ReviewsRepo;
import Project.Service.UserSevice;
import Project.message.Response;
import Project.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("rewiews/api/customer")
public class RestWebController {
	@Autowired
	public ReviewsRepo reviewsRepo;
	@Autowired
	private UserSevice userSevice;


	@GetMapping(value = "/all")
	public Response getResource() {
		Iterable<Reviews> custom = reviewsRepo.findAll();
		Response response = new Response("Done", custom);
		return response;
	}

	@PostMapping(value = "/save")
	public Response postCustomer(
			@AuthenticationPrincipal User user,
			@RequestBody Reviews customer) {

		Reviews reviews = new Reviews(customer.getReviewsOp(), user.getUsername(), userSevice.date());
		reviewsRepo.save(reviews);
		Response response = new Response("Done", customer);
		return response;
	}

}