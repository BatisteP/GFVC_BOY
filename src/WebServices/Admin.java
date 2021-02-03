package WebServices;

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
import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOChallenge;
import dao.DAOSuggestion;
import dao.DAOUser;
import model.Challenge;
import model.Obstacle;
import model.PointDePassage;
import model.Segment;
import model.Suggestion;
import model.User;

@Path("/admin")
public class Admin {

	DAOSuggestion s = new DAOSuggestion();
	DAOUser u = new DAOUser();
	DAOChallenge a = new DAOChallenge();
	/**
	 * 
	 * @return le json des utilisateurs
	 */
	@GET
	@Path( "getusers" )
	@Produces("text/json")
	public String getUsers (){
			String jsons="";
		
			
		
		
			ArrayList<User> list =  u.findAll();
			for (User a : list) {
				
				jsons+=a.toString()+"\n";
			}
		
		return jsons;
	 }
	/**
	 * 
	 * @param login
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws NotSupportedException
	 * @throws SystemException
	 * @throws RollbackException
	 * @throws HeuristicMixedException
	 * @throws HeuristicRollbackException
	 * @throws NamingException
	 */
	@GET
	@Path("removeuser")
	public void removeUser(@QueryParam("login") String login) throws SecurityException, IllegalStateException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException, NamingException {
		u.remove(u.find(login));
	}
	
	/**
	 *  permet de remplir la base de donnee avec un challenge elabore
	 * @return
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws NamingException
	 * @throws NotSupportedException
	 * @throws SystemException
	 * @throws RollbackException
	 * @throws HeuristicMixedException
	 * @throws HeuristicRollbackException
	 */
	@GET
	@Path ("createExampleChallenge")
	@Produces("text/json")
	public String example() throws SecurityException, IllegalStateException, NamingException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		//to do once to fill the database with a challenge
		PointDePassage p1 = new PointDePassage(1,null,null);
		PointDePassage p2 = new PointDePassage(2,null,null);
		PointDePassage p3 = new PointDePassage(3,null,null);
		PointDePassage p4 = new PointDePassage(4,null,null);
		PointDePassage p5 = new PointDePassage(5,null,null);
		Segment s1 = new Segment(100,p1,p2);
		s1.addObstacle(new Obstacle(1,"Tyrolienne avec les cables du tram"));
		s1.addObstacle(new Obstacle(2,"Quel est l'etre doue de la voix qui a quatre pieds le matin, deux à midi et trois le soir ?"));
		Segment s2 = new Segment(50,p1,p3);
		s2.addObstacle(new Obstacle(3,"Saut de l'ange : du 5eme etage. Pas pour les faibles."));
		s2.addObstacle(new Obstacle(4,"Parcours du combattant, ramper au milieu de la route entre les voitures (avec camouflage\"bitume\")."));
		Segment s3 = new Segment(40,p2,p4);
		s3.addObstacle(new Obstacle(5,"Toboggan catapule dirige vers L'Ill. (savoir nager)"));
		Segment s4 = new Segment(12,p3,p4);
		s4.addObstacle(new Obstacle(6,"Comment demarrer un serveur Payara. Test sur 3 OS differents randomises"));
		s4.addObstacle(new Obstacle(7,"Rodeo, tenir 5minutes. Vache non fournie."));
		Segment s5 = new Segment(42200,p4,p5);
		s5.addObstacle(new Obstacle(8,"Marathon."));
		p1.setChoixRapide(s1);
		p1.setChoixLent(s2);
		p2.setChoixRapide(s3);
		p3.setChoixRapide(s4);
		p4.setChoixRapide(s5);
		Challenge c = new Challenge(true,3,"Un challenge qui fait plaisir.");
		c.addPointDePassage(p1);
		c.addPointDePassage(p2);
		c.addPointDePassage(p3);
		c.addPointDePassage(p4);
		c.addPointDePassage(p5);
		a.create(c);
		return c.toString();
	}
	/**
	 * cree un challenge sans segment/pointdepassage voir les autres methodes (à developper pour enrichir le challenge)
	 * @param description
	 * @param tp
	 * @param ts
	 * @return jsons du challenge
	 */
	@GET
	@Path( "newchallenge" )
	@Produces("text/json")
	public String newChallenge (
			@QueryParam ("description") String description, @QueryParam ("teamPlay") boolean tp,@QueryParam ("teamSize") String ts) {
		
		Challenge c = null;
		int teamsize = Integer.parseInt(ts);
		try {
			
			c = new Challenge(tp,teamsize, description);
			a.create(c);
			
		} catch (NamingException | NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c.toString();
	 }
	
	/**
	 * 
	 * @return le json des suggestions
	 */
	@GET
	@Path( "getsuggestions" )
	@Produces("text/json")
	public String getSuggestions (){
			String jsons="";
			ObjectMapper mapper = new ObjectMapper();

			ArrayList<Suggestion> list =  s.findAll();
			for (Suggestion a : list) {
				
				try {
					  jsons += mapper.writeValueAsString(a);
					} catch (JsonProcessingException e) {
					   e.printStackTrace();
					}
			}
		
		return jsons;
	 }
	/**
	 * 
	 * @param id id de la suggestion
	 * @param verdict approve la suggestion (true) ou non (false)
	 * @return le json de la suggestion
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
	@Path( "approvesuggestion-{id}-{verdict}" )
	@Produces("text/json")
	public String approveSuggestions (@PathParam ("id") int id, @PathParam("verdict") Boolean verdict) throws SecurityException, IllegalStateException, NotSupportedException, SystemException, NamingException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
			Suggestion sugg = s.find(id);
			if (sugg == null) {
				return "suggestion doesn't exist!";
			};
			sugg.setAccepted(verdict);
			String jsons="";
			ObjectMapper mapper = new ObjectMapper();
			if (verdict.equals(false)){

				try {
					  jsons += mapper.writeValueAsString(sugg);
					  jsons += "has been disapproved by the judges.";
					} catch (JsonProcessingException e) {
					   e.printStackTrace();
					}
			}
			else if (verdict.equals(true)) {
				try {
					  jsons += mapper.writeValueAsString(sugg);
					  jsons += "has been approved by the judges.";
					} catch (JsonProcessingException e) {
					   e.printStackTrace();
					}
				}
			s.edit(sugg);
			return jsons;
		}

}
