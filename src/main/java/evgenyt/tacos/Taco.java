package evgenyt.tacos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity // JPA
public class Taco {
	
	// JPA 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private Long id;
	
	private Date createdAt;
	
	@NotNull
	@Size(min=5, message="Name must be at least 5 chcracters long")
	private String name;
	
	@ManyToMany(targetEntity=Ingredient.class) // JPA
	// @Size(min=1, message="You must choose at least 1 ingredient")
	private List<Ingredient> ingredients = new ArrayList<>();
	
	@PrePersist // JPA
	void createdAt() {
		this.createdAt = new Date();
	}	
}
