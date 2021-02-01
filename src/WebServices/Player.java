package WebServices;

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

import dao.DAOSuggestion;
import model.Suggestion;

@Path("/player")
public class Player {
	
	@GET
    @Produces("text/json")
    public String hello() {
        return "hello";
    }
	
	DAOSuggestion s = new DAOSuggestion();
	
	
	//api/player/modifierProfil{infos...} (modifier le profil avec les infos)-> api/player
	
	
	@GET
	@Path( "{login}/newInfos" )
	@Produces("text")
	public String modifyInfos() {
		
		
		return null;
		
	}
	
	
	@GET
	@Path( "/newsuggestion" )
	@Produces("text/json")
	public String newSuggestion (
			@QueryParam("login") String login, @QueryParam("description") String description) {

		try {
		s.create(new Suggestion(login,description));
		} catch (NamingException | NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "{done}";
	 }
	
	
}
