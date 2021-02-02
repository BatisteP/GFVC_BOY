package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.jasper.tagplugins.jstl.ForEach;

@Entity
@Table
public class Challenge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 	
	private int id;
	private boolean teamPlay;
	private int teamSize;
	private String description;
	private ArrayList<PointDePassage> points;
	private ArrayList<Segment> segments;
	//@ManyToMany(mappedBy = "challenges")
	@ManyToMany(
    cascade =
    {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    },
    mappedBy = "challenges")
	private Set<User>users;
	
public Challenge() {
		
	}
	
	public Challenge(int id, boolean teamPlay, int teamSize, String description, ArrayList<PointDePassage> pointsDePassages,
			ArrayList<Segment> segments) {
		this.id = id;
		this.teamPlay = teamPlay;
		this.teamSize = teamSize;
		this.description = description;
		this.points = pointsDePassages;
		this.segments = segments;
		
	}
	
	
	
	
	public Challenge(int id, boolean teamPlay, int teamSize, String description) {
		this.id = id;
		this.teamPlay = teamPlay;
		this.teamSize = teamSize;
		this.description = description;
		
		this.points = new ArrayList<>();
		this.segments = new ArrayList<>();
		this.users = new HashSet<User>();
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void addSegment(Segment seg) {
		segments.add(seg);
		points.add(seg.getArrivee());
		points.add(seg.getDepart());
		
	}


	public boolean isTeamPlay() {
		return teamPlay;
	}
	public void setTeamPlay(boolean teamPlay) {
		this.teamPlay = teamPlay;
	}
	public int getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}
	public String getDescription() {
		return description;
	}
	
	
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<PointDePassage> getPointsDePassages() {
		return points;
	}
	public void setPointsDePassages(ArrayList<PointDePassage> pointsDePassages) {
		this.points = pointsDePassages;
	}
	public ArrayList<Segment> getSegments() {
		return segments;
	}
	public void setSegments(ArrayList<Segment> segments) {
		this.segments = segments;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
	
	public String toString() {
		String str = "Challenge: \n";
		if(teamPlay) {
			str += "Jeu en équipes de: "+teamSize;
		}else {
			str += "Jeu pas en équipe.";
		}
		str+= "\n";
		str += this.description;
		for (PointDePassage pointDePassage : points) {
			str += pointDePassage;
		}
		str+= "\n";
		for (Segment s : this.segments) {
			str += s;
		}
		str+= "\n Joueurs inscrits: " + this.users.size()+"\n Voici la liste:";
		for (User u : this.users) {
			str += u.getLogin() + "\n";
		}
		return str;
		
	}












	
	
	

}
