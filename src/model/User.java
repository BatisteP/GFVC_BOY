package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 	
	private String login;
	private String password;
	private String lastname;
	private String firstname;
	private Boolean isAdmin;
	private Map<Integer/*ID du challenge*/,Integer/*id du point de passage où tu es courrament*/> avancement;
	/*
	//@OneToMany (orphanRemoval=true, mappedBy = "challenges");
	@JoinTable(name = "store_product");
	private ArrayList<Challenge> challenges;
	cascade = {
		    CascadeType.PERSIST,
		    CascadeType.DETACH
		}*/
	
	@ManyToMany(
	        cascade =
	        {
	                CascadeType.DETACH,
	                CascadeType.MERGE,
	                CascadeType.REFRESH,
	                CascadeType.PERSIST
	        })
	@JoinTable(
			name = "inscription",
			joinColumns = @JoinColumn(name = "user_login"),
			inverseJoinColumns = @JoinColumn(name = "challenge_id"))
	private Set<Challenge> challenges;
	
	
	
	
	
	
	public User() {	
	}
	/**
	 *
	 * @param login clé primaire dans la DB
	 * @param password 
	 * @param lastname
	 * @param firstname
	 * @param isAdmin true : admin false : player
	 * lié dans la BDD à challenge par la table inscription
	 * @see Challenge
	 */
	public User(String login, String password, String lastname, String firstname, Boolean isAdmin) {
		super();
		this.login = login;
		this.password = password;
		this.lastname = lastname;
		this.firstname = firstname;
		this.isAdmin = isAdmin;
		this.challenges = new HashSet<>();
		this.avancement = new HashMap<Integer,Integer>();
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	public void changeAvancement(int challengeid,int newavancement) {
		avancement.put(challengeid, newavancement);
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public void addChallenge(Challenge c) {
		this.challenges.add(c);
		this.avancement.put(c.getId(),1);
	}
	
	public Map<Integer, Integer> getAvancement() {
		return avancement;
	}

	public void setAvancement(Map<Integer, Integer> avancement) {
		this.avancement = avancement;
	}

	public void removeChallenge(Challenge c) {
		this.challenges.remove(c);
	}
	
	public Set<Challenge> getChallenges() {
		return this.challenges;
	}
	public String toString() {
		String jsons ="{\n";
		jsons+= "\nlogin : "+login;
		jsons+= "\npassword : "+password;
		jsons+= "\nlastname : "+lastname;
		jsons+= "\nfirstname : "+firstname;
		jsons+= "\nisAdmin : "+isAdmin;
		jsons+= "\ninscrit dans les challenges : [";
	    for (Challenge c : challenges) {
	    	jsons+= c.getId()+",";
	    }
	    jsons+= "]";
	    jsons+= "\navancement : [";
	    for (Map.Entry mapentry : avancement.entrySet()) {
	           System.out.println("clé: "+mapentry.getKey() 
	                              + " | valeur: " + mapentry.getValue());
	           
	           jsons+=mapentry.getKey()+"->"+mapentry.getValue()+" ";
	        }
	    jsons+= "]";
		return jsons+"\n}";
	}
	@Override
	public int hashCode() {
		return 0;
	}
	
}
