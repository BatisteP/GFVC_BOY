package WebServices;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
			ObjectMapper mapper = new ObjectMapper();
			
		
		
			ArrayList<User> list =  u.findAll();
			for (User a : list) {
				
				try {
					  jsons += mapper.writeValueAsString(a);
					  //System.out.println("ResultingJSONstring = " + json);
					  //System.out.println(json);
					} catch (JsonProcessingException e) {
					   e.printStackTrace();
					}
			}
		
		return jsons;
	 }

}
