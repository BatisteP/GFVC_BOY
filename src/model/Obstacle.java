package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Obstacle implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4227824923842522157L;
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
