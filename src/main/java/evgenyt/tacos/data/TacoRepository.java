package evgenyt.tacos.data;

import evgenyt.tacos.Taco;
import org.springframework.data.repository.CrudRepository;

/**
 * Work with Taco table 
 */

public interface TacoRepository
	extends CrudRepository<Taco, Long> {
}
