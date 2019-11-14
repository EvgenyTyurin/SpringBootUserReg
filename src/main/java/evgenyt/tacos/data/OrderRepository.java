package evgenyt.tacos.data;

import evgenyt.tacos.Order;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Work with Order table with Spring Data and JPA
 */

public interface OrderRepository
	extends CrudRepository<Order, Long> {
	
	// Find order by name
	List<Order> findByName(String name);
	
	// Find order by name and date
	List<Order> readOrdersByNameAndPlacedAtBetween(
			String deliveryZip, Date startDate, Date endDate);
	
	// SQL query
	@Query("SELECT o FROM Order o WHERE o.address='Kurgan city'")
	List<Order> readOrdersDeliveredInKurgan();	
	
}
