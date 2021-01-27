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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import dao.DAOChallenge;

@Path("/vitrine")
public class Challenges {
	
	DAOChallenge a = new DAOChallenge();
	
	@GET
	@Path( "newchallenge-{id}" )
	@Produces("text/json")
	public String newVolById (
			@PathParam ("id") String id) {
		
		
		int i = Integer.parseInt(id);
		try {
			
			
			a.create(new Challenge(i,true,10, "Test de souffle apres confinement",new PointDePassage(0, null, null)));
		} catch (NamingException | NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "{done}";
	 }
	
	
	@GET
	@Path( "getchallenges" )
	@Produces("text/json")
	public String getChallenges (){
			String jsons="";
		
		
		
			ArrayList<Challenge> list =  a.findAll();
			for (Challenge a : list) {
				jsons += "\n \"id du challenge\"" + "\""+a.getId() +"\"";
				jsons += "\n \"description : \"" + "\""+a.getDescription() +"\"";
				jsons += "\n \"nombre de gens\"" + "\""+a.getTeamSize() +"\"";
				jsons += "\n";
			}
		
		return jsons;
	 }
	
	
}
