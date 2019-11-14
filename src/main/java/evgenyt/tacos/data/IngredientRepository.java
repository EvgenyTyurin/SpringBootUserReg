package evgenyt.tacos.data;

import evgenyt.tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;

/**
 * Work with Ingredients table 
 */

public interface IngredientRepository
	extends CrudRepository<Ingredient, String> {
}
