package WebServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/player")
public class Player {
	//api/player/modifierProfil{infos...} (modifier le profil avec les infos)-> api/player
	@GET
	@Path( "{login}/newInfos" )
	@Produces("text")
	public String modifyInfos() {
		
		
		return null;
		
	}
	
}
