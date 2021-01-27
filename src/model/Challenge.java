package model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
public Challenge() {
		
	}
	//private PointDePassage depart;
	/*public PointDePassage getDepart() {
		return depart;
	}
	




	public void setDepart(PointDePassage depart) {
		this.depart = depart;
	}
*/



	public Challenge(int id, boolean teamPlay, int teamSize, String description, ArrayList<PointDePassage> pointsDePassages,
			ArrayList<Segment> segments, PointDePassage pdp) {
		this.id = id;
		this.teamPlay = teamPlay;
		this.teamSize = teamSize;
		this.description = description;
		this.points = pointsDePassages;
		this.segments = segments;
		//this.depart = pdp;
	}
	
	
	
	
	public Challenge(int id, boolean teamPlay, int teamSize, String description, PointDePassage pdp) {
		this.id = id;
		this.teamPlay = teamPlay;
		this.teamSize = teamSize;
		this.description = description;
		//this.depart = pdp;
		this.points = new ArrayList<>();
		this.segments = new ArrayList<>();
		
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
	
	
	

}
