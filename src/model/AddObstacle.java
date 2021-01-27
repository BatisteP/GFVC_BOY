package model;

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
import dao.DAOObstacle;

@Path("/vitrine")
public class AddObstacle {
	
	DAOObstacle a = new DAOObstacle();
	
	@GET
	@Path( "newobstacle-{id}" )
	@Produces("text/json")
	public String newVolById (
			@PathParam ("id") String id) {
		
		
		int i = Integer.parseInt(id);
		try {
			
			
			a.create(new Obstacle(i,"12122"));
		} catch (NamingException | NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "{done}";
	 }
}
