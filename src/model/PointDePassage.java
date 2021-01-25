package model;

public class PointDePassage {
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
	public PointDePassage(Segment choixRapide, Segment choixLent) {
		super();
		this.choixRapide = choixRapide;
		this.choixLent = choixLent;
	}
	

}
