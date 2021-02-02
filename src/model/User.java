package model;

import java.util.ArrayList;
import java.util.HashSet;
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
	
	public User(String login, String password, String lastname, String firstname, Boolean isAdmin) {
		super();
		this.login = login;
		this.password = password;
		this.lastname = lastname;
		this.firstname = firstname;
		this.isAdmin = isAdmin;
		this.challenges = new HashSet<>();
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
	}
	
	public void removeChallenge(Challenge c) {
		this.challenges.remove(c);
	}
	
	public Set<Challenge> getChallenges() {
		return this.challenges;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
	
}
