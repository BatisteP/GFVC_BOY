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
	
	@GET
    @Produces("text/json")
    public String hello() {
        return "hello";
    }
	
	DAOSuggestion s = new DAOSuggestion();
	DAOChallenge c = new DAOChallenge();
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
		User u2 = new User(login, password, lastname, firstname, false);
		u2.setIsAdmin(user.getIsAdmin());
		u.edit(u2);
		String jsons="";
		ObjectMapper mapper = new ObjectMapper();

			try {
				  jsons += mapper.writeValueAsString(u2);
				  //System.out.println("ResultingJSONstring = " + json);
				  //System.out.println(json);
				} catch (JsonProcessingException e) {
				   e.printStackTrace();
				}
		
	
		return jsons;
	}
	
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
		String jsons="";
		ObjectMapper mapper = new ObjectMapper();

			try {
				  jsons += mapper.writeValueAsString(user);
				  jsons += "has successfully left us.";
				  //System.out.println("ResultingJSONstring = " + json);
				  //System.out.println(json);
				} catch (JsonProcessingException e) {
				   e.printStackTrace();
				}
		
	
		return jsons;
	}
	
	
	
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
	
	@GET
	@Path("/getMyChallenges")
	@Produces("text/json")
	public String getChallenges(@QueryParam("login") String login) throws SecurityException, IllegalStateException, NotSupportedException, SystemException, NamingException, RollbackException, HeuristicMixedException, HeuristicRollbackException{
		User user = u.find(login);
		if (user == null) {
			return "user doesn't exist!";
		};
		String jsons="";
		ObjectMapper mapper = new ObjectMapper();

			jsons += "Votre login: "+user.getLogin();
			jsons += "\nVos challenges: ";
			for (Challenge c : user.getChallenges()) {
				jsons += c;
				jsons += "\n";
			}
		
	
		return jsons;	
	}
	
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
			return user.getLogin()+ "inscrit à challenge " + challenge.getId();
		}
		return "Challenge ou utilisateur inconnu.";
	}
	
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
			//user.removeChallenge(challenge);
			u.edit(user);
			return "Vous êtes "+login+" et vous avez quitté  le challenge "+id+"    "+ user.getChallenges().size();
		}
		return "Challenge ou utilisateur inconnu.";
	}
	
	
}
