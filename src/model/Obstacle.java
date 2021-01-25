package model;

public class Obstacle {
	private int id;
	private String description;
	
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
