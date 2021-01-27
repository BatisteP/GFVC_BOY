package model;

import java.util.ArrayList;

import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import dao.DAOUser;

@Path("/vitrine")
public class Users {
	
	DAOUser a = new DAOUser();
	
	@GET
	@Path( "newuser" )
	@Produces("text/json")
	public String newUser (
			@QueryParam("login") String login, @QueryParam("password") String password, @QueryParam("lastname") String lastname, @QueryParam("firstname") String firstname) {

		try {
			
			
		a.create(new User(login,password,lastname,firstname));
		} catch (NamingException | NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "{done}";
	 }
	
	
	@GET
	@Path( "getusers" )
	@Produces("text/json")
	public String getUsers (){
			String jsons="";
		
		
		
			ArrayList<User> list =  a.findAll();
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
