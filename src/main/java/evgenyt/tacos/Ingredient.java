package evgenyt.tacos;

import javax.persistence.Entity;
import javax.persistence.Id;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

// @Data

// JPA annotations
@Entity
// We still need args constructor 
// @RequiredArgsConstructor
// JPA requires that entities have a noarguments constructor, 
// so Lombok’s @NoArgsConstructor does that
// @NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Ingredient {
	@Id // JPA needs ID field
	private String id;
	private String name;
		
	public Ingredient() {
	}
	
	public Ingredient(String id, String name) {
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
