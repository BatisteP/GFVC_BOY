package WebServices;

import java.util.ArrayList;

import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUser;
import model.User;

@Path("/player")
public class Player {
	
	@GET
	@Produces("text/json")
	public String hello() {
		return "hello";
	}
	DAOUser u = new DAOUser();
	//api/player/modifierProfil{infos...} (modifier le profil avec les infos)-> api/player
	@GET
	@Path( "/{login}/newInfos" )
	@Produces("text/json")
	public String modifyInfos(@PathParam ("login") String login, @QueryParam("currentpassword") String currpassword,@QueryParam("newpassword") String password, @QueryParam("lastname") String lastname, @QueryParam("firstname") String firstname) throws SecurityException, IllegalStateException, NotSupportedException, SystemException, NamingException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		
		User user = u.find(login);
		if (user == null) {
			return "user doesn't exist!";
		};
		if (!user.getPassword().equals(currpassword)) {
			return "wrong password";
		};
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setPassword(password);
		u.edit(user);
		String jsons="";
		ObjectMapper mapper = new ObjectMapper();
		
	
	
		
			
			try {
				  jsons += mapper.writeValueAsString(user);
				  //System.out.println("ResultingJSONstring = " + json);
				  //System.out.println(json);
				} catch (JsonProcessingException e) {
				   e.printStackTrace();
				}
		
	
	return jsons;		
	}
	
}
