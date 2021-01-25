package model;

import java.util.ArrayList;

public class Challenge {
	private int id;
	private boolean teamPlay;
	private int teamSize;
	private String description;
	private ArrayList<PointDePassage> pointsDePassages;
	private ArrayList<Segment> segments;
	private PointDePassage depart;
	public PointDePassage getDepart() {
		return depart;
	}




	public void setDepart(PointDePassage depart) {
		this.depart = depart;
	}




	public Challenge(int id, boolean teamPlay, int teamSize, String description, ArrayList<PointDePassage> pointsDePassages,
			ArrayList<Segment> segments, PointDePassage pdp) {
		super();
		this.id = id;
		this.teamPlay = teamPlay;
		this.teamSize = teamSize;
		this.description = description;
		this.pointsDePassages = pointsDePassages;
		this.segments = segments;
		this.depart = pdp;
	}
	
	
	
	
	public Challenge(int id, boolean teamPlay, int teamSize, String description, PointDePassage pdp) {
		super();
		this.id = id;
		this.teamPlay = teamPlay;
		this.teamSize = teamSize;
		this.description = description;
		this.depart = pdp;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void addSegment(Segment seg) {
		segments.add(seg);
		pointsDePassages.add(seg.getArrivee());
		pointsDePassages.add(seg.getDepart());
		
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
		return pointsDePassages;
	}
	public void setPointsDePassages(ArrayList<PointDePassage> pointsDePassages) {
		this.pointsDePassages = pointsDePassages;
	}
	public ArrayList<Segment> getSegments() {
		return segments;
	}
	public void setSegments(ArrayList<Segment> segments) {
		this.segments = segments;
	}
	
	
	

}
