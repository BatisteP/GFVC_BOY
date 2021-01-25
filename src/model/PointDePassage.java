package model;

public class PointDePassage {
	private int id;
	Segment choixRapide;
	Segment choixLent;
	public Segment getChoixRapide() {
		return choixRapide;
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
	

}
