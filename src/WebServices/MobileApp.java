package WebServices;

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
import javax.ws.rs.QueryParam;

import dao.DAOChallenge;
import dao.DAOUser;
import model.Challenge;
import model.PointDePassage;
import model.User;

@Path("/mobileapp")
public class MobileApp {
	DAOUser u = new DAOUser();
	DAOChallenge c = new DAOChallenge();
	
	@GET
    @Produces("text/json")
    public String hello() {
        return "hello";
    }
	
	@Path("/{login}/challenge-{id}-{modededeplacement}")
	@Produces("text/json")
	public String progress (@PathParam("login") String login,@PathParam("id") String id,@PathParam("modedeplacement") String modedeplacement,@QueryParam("newetape") String newetape) throws SecurityException, IllegalStateException, NotSupportedException, SystemException, NamingException, RollbackException, HeuristicMixedException, HeuristicRollbackException{
		
		int iD = Integer.parseInt(id);
		int etape  = Integer.parseInt(newetape);
		User user = u.find(login);
		Challenge chall = c.find(iD);
		if (chall ==null) {
			return "challenge doesn't exist";
		}
		if (user == null) {
			return "user doesn't exist";
		}
		boolean flag = false;
		for (PointDePassage p: chall.getPointsDePassages()) {
			if (p.getId() == etape) flag = true;
		}
		if (!flag) {
			return "this step doesn't exist in the challenge!";
			
		}
		user.getAvancement().put(chall.getId(),etape);	
		u.edit(user);
		
		return "well done ! Mode de déplacement : "+ modedeplacement+"\n"+user.toString();
	}
	
	
}
