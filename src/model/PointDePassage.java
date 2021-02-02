package model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PointDePassage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1954765070382505217L;
	@GeneratedValue(strategy = GenerationType.AUTO) 	
	private int id;
	Segment choixRapide;
	Segment choixLent;
	public Segment getChoixRapide() {
		return choixRapide;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setChoixRapide(Segment choixRapide) {
		this.choixRapide = choixRapide;
	}
	public Segment getChoixLent() {
		return choixLent;
	}
	public void setChoixLent(Segment choixLent) {
		this.choixLent = choixLent;
	}
	public PointDePassage(int id,Segment choixRapide, Segment choixLent) {
		this.id = id;
		this.choixRapide = choixRapide;
		this.choixLent = choixLent;
	}
	 public String toString() {
	        String jsons ="{";
	        jsons +="\nPoint_De_Passage :  \"" +id+"\"";
	        if (choixRapide!=null)jsons +="\nchoixRapide : " +choixRapide.toString();
	        if (choixLent!=null)  jsons +="\nchoixLent : " +choixLent.toString();
	       
	        

	        
	    return jsons+"}";
	        
	    } 
}
