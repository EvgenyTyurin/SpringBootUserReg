	package evgenyt.tacos;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

/**
 * Show order form (orderForm.html and process user input)
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import evgenyt.tacos.data.OrderRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order") // order is permanent across session
public class OrderController {
	
	// to connect database
	private OrderRepository orderRepo;
		
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}

	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("message", "Make your order!");
		return("orderForm");
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, Model model, 
			SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
		if (errors.hasErrors()) {			
			log.info("Errors in order input" + errors.toString());			
			model.addAttribute("message", errors.getAllErrors().toString());
			return("orderForm");
		}
		log.info("Process order: " + order);
		// save order with taco in databse
		order.setUser(user);
		orderRepo.save(order);
		sessionStatus.setComplete();
		return("redirect:/");
	}

}
