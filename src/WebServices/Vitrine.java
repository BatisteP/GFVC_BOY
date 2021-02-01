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
import dao.DAOUser;
import model.Challenge;
import model.PointDePassage;
import model.User;

@Path("/vitrine")
public class Vitrine {
	
	DAOChallenge a = new DAOChallenge();
	DAOUser u = new DAOUser();
	
	
	
	
	@GET
	@Path( "getchallenges" )
	@Produces("text/json")
	public String getChallenges (){
        String jsons="";
    
    
    
        ArrayList<Challenge> list =  a.findAll();
        for (Challenge a : list) {
            jsons += "\n \"id du challenge\"" + "\""+a.getId() +"\"";
            jsons += "\n \"description : \"" + "\""+a.getDescription() +"\"";
            jsons += "\n \"nombre de gens :\"" + "\""+a.getTeamSize() +"\"";
            jsons += "\n \"Points de Passage :\"" + "\""+a.getPointsDePassages() +"\"";
           // jsons += "\n \"Segments : \"" + "\""+a.getSegments() +"\"";
            jsons += "\n";
        }
    
    return jsons;
 } 

	/* @Inject
	private KeyGenerator keyGenerator;*/
	 
	
	
	@GET
	@Path( "newuser" )
	@Produces("text/json")
	public String newUser (
			@QueryParam("login") String login, @QueryParam("password") String password, @QueryParam("lastname") String lastname, @QueryParam("firstname") String firstname, @QueryParam("isAdmin") String isAdmin) {

		try {
			Boolean flag;
		if (isAdmin.equals("yes")) {
			flag = true;
		}
		else {flag =false;}
		
		u.create(new User(login,password,lastname,firstname,flag));
		} catch (NamingException | NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "{done}";
	 }
	
	
	
	/*@POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("login") String login,
                                     @FormParam("password") String password) {
        try {
 
            // Authenticate the user using the credentials provided
            authenticate(login, password);
 
            // Issue a token for the user
            String token = issueToken(login);
 
            // Return the token on the response
            return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
 
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
	 private void authenticate(String login, String password) throws Exception {
		User user =a.find(login);
		if (user==null) {	throw new Exception("user doesn't exist");}
		else if( !user.getPassword().equals(password))
		
		{throw new Exception("wrong password!");}
		
		
	}


	private String issueToken(String login) throws NoSuchAlgorithmException {

		
		 Date d = new Date();
	     Key key = keyGenerator.generateKey();
	        String jwtToken = Jwts.builder()
	                .setSubject(login)
	                .setIssuer(login)
	                .setIssuedAt(d)
	                .setExpiration(new Date(d.getTime()+300000))
	                .signWith(SignatureAlgorithm.HS512, key)
	                .compact();
	        return jwtToken;
		 
		
	 }*/
}