package model;

public class Obstacle {
	private int id;
	private String description;
	
	public Obstacle(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
