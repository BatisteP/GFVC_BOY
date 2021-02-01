package WebServices;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import dao.DAOUser;
import model.User;

@Path("/admin")
public class Admin {
	DAOUser u = new DAOUser();
	@GET
	@Path( "getusers" )
	@Produces("text/json")
	public String getUsers (){
			String jsons="";
		
		
		
			ArrayList<User> list =  u.findAll();
			for (User a : list) {
				jsons += "\n \"Le login\"" + "\""+a.getLogin() +"\"";
				jsons += "\n \"Le mdp : \"" + "\""+a.getPassword() +"\"";
				jsons += "\n \"Nom\"" + "\""+a.getLastname() +"\"";
				jsons += "\n \"Prénom\"" + "\""+a.getFirstname() +"\"";
				jsons += "\n";
			}
		
		return jsons;
	 }

}
