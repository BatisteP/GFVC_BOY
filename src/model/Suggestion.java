package model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Suggestion {
	@Id
	String login;
	String description;
	
	public Suggestion(String login, String description) {
		super();
		this.login=login;
		this.description=description;
		
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String toString() {
		String jsons="{";
		jsons+="Username : \"" +login+"\"";
		jsons+="Description : \"" +description+"\"";
    return jsons+"}";
}
}
