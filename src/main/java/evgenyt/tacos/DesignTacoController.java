package evgenyt.tacos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import evgenyt.tacos.data.IngredientRepository;
import evgenyt.tacos.data.TacoRepository;
// For logging
import lombok.extern.slf4j.Slf4j;


// This class will control "/design" http requests

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order") // order is permanent across session
public class DesignTacoController {
		
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	// For work with database
	private final IngredientRepository ingredientRepo;
	List<Ingredient> ingredientList = new ArrayList<>();
	private TacoRepository designRepo;
	
	@Autowired
	public DesignTacoController(
			IngredientRepository ingredientRepo,
			TacoRepository designRepo) {
		this.ingredientRepo = ingredientRepo;
		this.designRepo = designRepo;
	}

	// This method control GET http request
	// and prepare data fo input form
	@GetMapping
	public String showDesignForm(Model model) {
		// Get ingredient list from database
		ingredientList.clear();
		ingredientRepo.findAll().forEach(i -> ingredientList.add(i));		
		model.addAttribute("ingredientList", ingredientList);
		// Add text field to model
		model.addAttribute("message", "Design you shaverma!");
		// Pass data to view "design.html"
		return "design";
	}
	
	// This method handle POST method with data from input FORM
	@PostMapping
	public String processDesign(@Valid Taco taco, Errors errors, Model model,
			@ModelAttribute Order order) {
		if (errors.hasErrors()) {
			log.info("!Errors in user input" + errors.toString());
			// Add text with error messages field to model
			model.addAttribute("message", errors.toString());
			// Add Taco object to model (name design is a must)
			model.addAttribute("design", taco);
			model.addAttribute("ingredientList", ingredientList);
			// Pass data to view "design.html"
			return "design";
		}
		// We get Taco object from user input
		log.info("Taco name: " + taco.getName());
		log.info("Taco ingredients: " + taco.getIngredients());
		// Save taco to Db
		Taco saved = designRepo.save(taco);
		log.info("Taco db id: " + taco.getId());
		// Proceed to order form
		order.addDesign(saved);
		return("redirect:/orders/current");
	}

}
