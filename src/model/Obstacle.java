package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Obstacle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 	
	private int id;
	private String description;
	public Obstacle() {};
	public Obstacle(int id,String description) {
		this.id = id;
		this.description = description;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
