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


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUser;
import model.User;
import dao.DAOChallenge;
import dao.DAOSuggestion;
import model.Challenge;
import model.Suggestion;


@Path("/player")
public class Player {
	
	
	DAOSuggestion s = new DAOSuggestion();
	DAOChallenge c = new DAOChallenge();
	DAOUser u = new DAOUser();
	

	//api/player/modifierProfil{infos...} (modifier le profil avec les infos)-> api/player
	
	/**
	 * modifie les infos d'un membre
	 * @param login
	 * @param currpassword vérifie qu'il connait le password
	 * @param password le nouveau password
	 * @param lastname le nouveau nom
	 * @param firstname le nouveau prénom
	 * @return jsons de l'user
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws NotSupportedException
	 * @throws SystemException
	 * @throws NamingException
	 * @throws RollbackException
	 * @throws HeuristicMixedException
	 * @throws HeuristicRollbackException
	 */
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
		User u2 = new User(login, password, lastname, firstname, false);
		u2.setIsAdmin(user.getIsAdmin());
		u.edit(u2);
		
		
	
		return u2.toString();
	}
	/**
	 * désinscription de l'application
	 * @param login
	 * @param currpassword
	 * @return
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws NotSupportedException
	 * @throws SystemException
	 * @throws NamingException
	 * @throws RollbackException
	 * @throws HeuristicMixedException
	 * @throws HeuristicRollbackException
	 */
	@GET
	@Path( "/{login}/quit" )
	@Produces("text/json")
	public String quitApplication(@PathParam ("login") String login, @QueryParam("currentpassword") String currpassword) throws SecurityException, IllegalStateException, NotSupportedException, SystemException, NamingException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		User user = u.find(login);
		
		if (user == null) {
			return "user doesn't exist!";
		};
		if (!user.getPassword().equals(currpassword)) {
			return "wrong password";
		};
		
		u.remove(user);
		String jsons=user.toString();
		
		jsons += "has successfully left us.";
			
	
		return jsons;
	}
	
	
	/**
	 * crée un nouvelle suggestion, soumise au vote des autres utilisateurs, et à ^étre acceptée par l'admin
	 * @param login
	 * @param description
	 * @return message ok
	 * @see Admin#approveSuggestions(int, Boolean)
	 * @see #voteSugestion(String, String, String)
	 */
	@GET
	@Path( "/newsuggestion" )
	@Produces("text/json")
	public String newSuggestion (
			@QueryParam("login") String login, @QueryParam("description") String description) {
		
		try {
			User user = u.find(login);
			if (user == null) {
				return "user doesn't exist!";
			};
		s.create(new Suggestion(login,description,false));
		} catch (NamingException | NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "{done}";
	 }
	/**
	 * renvoie les challenges auxquels l'utilisateur est inscrit
	 * @param login
	 * @return
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws NotSupportedException
	 * @throws SystemException
	 * @throws NamingException
	 * @throws RollbackException
	 * @throws HeuristicMixedException
	 * @throws HeuristicRollbackException
	 */
	@GET
	@Path("/getMyChallenges")
	@Produces("text/json")
	public String getChallenges(@QueryParam("login") String login) throws SecurityException, IllegalStateException, NotSupportedException, SystemException, NamingException, RollbackException, HeuristicMixedException, HeuristicRollbackException{
		User user = u.find(login);
		if (user == null) {
			return "user doesn't exist!";
		};
		String jsons="";
		
			jsons += "Votre login: "+user.getLogin();
			jsons += "\nVos challenges: ";
			for (Challenge c : user.getChallenges()) {
				jsons += c;
				jsons += "\n";
			}
		
	
		return jsons;	
	}
	/**
	 * inscription à un challenge si celui-ci existe
	 * @param login
	 * @param id
	 * @return
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws NotSupportedException
	 * @throws SystemException
	 * @throws NamingException
	 * @throws RollbackException
	 * @throws HeuristicMixedException
	 * @throws HeuristicRollbackException
	 */
	@GET
	@Path( "/chooseChallenge")
	@Produces("text/json")
	public String addChallenge(@QueryParam("login") String login,@QueryParam("id") String id) throws SecurityException, IllegalStateException, NotSupportedException, SystemException, NamingException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		int i = Integer.parseInt(id);
		User user = u.find(login);
		Challenge challenge = c.find(i);
		if (user != null && challenge != null) {
			user.addChallenge(challenge);
			u.edit(user);
			return user.getLogin()+ "inscrit Ã  challenge " + challenge.getId();
		}
		return "Challenge ou utilisateur inconnu.";
	}
	/**
	 * désinscription à un challenge si celui-ci existe
	 * @param login
	 * @param id
	 * @return
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws NotSupportedException
	 * @throws SystemException
	 * @throws NamingException
	 * @throws RollbackException
	 * @throws HeuristicMixedException
	 * @throws HeuristicRollbackException
	 */
	@GET
	@Path ("/removeChallenge")
	@Produces("text/json")
	public String removeChallenge(@QueryParam("login") String login, @QueryParam("id") String id) throws SecurityException, IllegalStateException, NotSupportedException, SystemException, NamingException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		User user = u.find(login);
		Challenge challenge = c.find(Integer.parseInt(id));
		if (user != null && challenge != null) {
			Boolean lol = user.getChallenges().contains(challenge);
			for(Challenge c : user.getChallenges()) {
				if (c.getId() == Integer.parseInt(id)) user.removeChallenge(c);
			}
			user.removeChallenge(challenge);
			u.edit(user);
			return "Vous Ãªtes "+login+" et vous avez quittÃ©  le challenge "+id+"    "+ user.getChallenges().size();
		}
		return "Challenge ou utilisateur inconnu.";
	}
	/**
	 * permet de voter pour ou contre une suggestion
	 * @param login
	 * @param id
	 * @param vote
	 * @return
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws NotSupportedException
	 * @throws SystemException
	 * @throws NamingException
	 * @throws RollbackException
	 * @throws HeuristicMixedException
	 * @throws HeuristicRollbackException
	 */
	@GET
	@Path ("/{login}/voteSuggestion-{id}")
	@Produces("text/json")
	public String voteSugestion(@PathParam ("login") String login,@PathParam ("id") String id, @QueryParam("vote") String vote) throws SecurityException, IllegalStateException, NotSupportedException, SystemException, NamingException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		
		User user = u.find(login);
		Suggestion sugg = s.find(Integer.parseInt(id));
	
		if (user != null && sugg != null) {
			boolean flag;
			if (vote.equals("yes")) sugg.incrVotePour();
			else sugg.incrVoteContr();
			s.edit(sugg);
		}
		
		else {
			return "this suggestion doesn't exist, or you don't exist";
		}
		ObjectMapper mapper = new ObjectMapper();
		String jsons="";
		try {
			  jsons += mapper.writeValueAsString(sugg);
			  //System.out.println("ResultingJSONstring = " + json);
			  //System.out.println(json);
			} catch (JsonProcessingException e) {
			   e.printStackTrace();
			}
		return jsons;
		
		
	}

	
	
}
