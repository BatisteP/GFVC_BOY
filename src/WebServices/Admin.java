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
	
	@GET
	@Path("removeuser")
	public void removeUser(@QueryParam("login") String login) throws SecurityException, IllegalStateException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException, NamingException {
		u.remove(u.find(login));
	}
	
	@GET
	@Path( "newchallenge-{id}" )
	@Produces("text/json")
	public String newChallengeById (
			@PathParam ("id") String id) {
		
		
		int i = Integer.parseInt(id);
		try {
			
			ArrayList<Obstacle> l= new ArrayList<Obstacle>();
			l.add(new Obstacle(1,"ring of fire : extincteur non fourni"));
			Segment s = new Segment(1, l, null, null);
			PointDePassage p1 =new PointDePassage(1,s,null);
			PointDePassage p2 = new PointDePassage(2,s,null);
			s.setDepart(p1);
			s.setArrivee(p2);
			 ArrayList<PointDePassage> pointsDePassages = new  ArrayList<PointDePassage>();
			 pointsDePassages.add(p1);
			 pointsDePassages.add(p2);
	
			 ArrayList<Segment> seg = new ArrayList<Segment>();
			 seg.add(s);
			//a.create(new Challenge(i,true,10, "Test de souffle apres confinement"));
			a.create(new Challenge(i,true,10, "Test de souffle apres confinement",pointsDePassages,seg));
		} catch (NamingException | NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "{done}";
	 }
	
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
