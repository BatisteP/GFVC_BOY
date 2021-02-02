package model;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Segment implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 56916071436599392L;
	private int distance;
	private ArrayList<Obstacle> obstacles;
	private PointDePassage depart;
	private PointDePassage arrivee;
	
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}
	public void setObstacles(ArrayList<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}
	public void addObstacle (Obstacle obs) {
		obstacles.add(obs);
	}
	public PointDePassage getDepart() {
		return depart;
	}
	public void setDepart(PointDePassage depart) {
		this.depart = depart;
	}
	public PointDePassage getArrivee() {
		return arrivee;
	}
	public void setArrivee(PointDePassage arrivee) {
		this.arrivee = arrivee;
	}
	public Segment(int distance, ArrayList<Obstacle> obstacles, PointDePassage depart, PointDePassage arrivee) {
		this.distance = distance;
		this.obstacles = obstacles;
		this.depart = depart;
		this.arrivee = arrivee;
	}
	public Segment(int distance, PointDePassage depart, PointDePassage arrivee) {
		this.distance = distance;
		this.depart = depart;
		this.arrivee = arrivee;
		this.obstacles = new ArrayList<> ();
		
	}
	public String toString() {
		String jsons="{";
		jsons+="distance : "+getDistance();
	    
		
        for (Obstacle a : obstacles) {
        	jsons+= "\nobstacle : "+a.getDescription();
           
        
        }
        if (this.depart!=null) jsons+= "\npoint_depart "+ this.depart.getId();
        if (this.arrivee!=null) jsons+= "\npoint_arrivee "+ this.arrivee.getId();
        
    
    return jsons+"}";
	}
	
	
	
}
