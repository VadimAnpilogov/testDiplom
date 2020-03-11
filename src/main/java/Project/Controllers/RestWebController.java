package Project.Controllers;

import Project.Entity.Reviews;
import Project.Repository.ReviewsRepo;
import Project.message.Response;
import Project.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("rewiews/api/customer")
public class RestWebController {
	@Autowired
	public ReviewsRepo reviewsRepo;

	List<Reviews> cust = new ArrayList<Reviews>();

	@GetMapping(value = "/all")
	public Response getResource() {
		Iterable<Reviews> custom = reviewsRepo.findAll();
		Response response = new Response("Done", custom);
		return response;
	}

	@PostMapping(value = "/save")
	public Response postCustomer(@RequestBody Reviews customer) {
		cust.add(customer);

		// Create Response Object
		Response response = new Response("Done", customer);
		return response;
	}


//	List<Customer> cust = new ArrayList<Customer>();
//
//	@GetMapping(value = "/all")
//	public Response getResource() {
//		Response response = new Response("Done", cust);
//		return response;
//	}
//
//	@PostMapping(value = "/save")
//	public Response postCustomer(@RequestBody Customer customer) {
//		cust.add(customer);
//
//		// Create Response Object
//		Response response = new Response("Done", customer);
//		return response;
//	}
}