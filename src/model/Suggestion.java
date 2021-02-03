package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Suggestion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 	
	int id;
	String login;
	String description;
	int votesPour = 0;
	int votesContre= 0;
	Boolean accepted;

    public Suggestion() {

    }
    /**
     * 
     * @param login 
     * 		l'utilisateur
     * @param description 
     * @param accepted 
     * 		état de la suggestion, à accepter par l'admin
     */
    public Suggestion(String login, String description,Boolean accepted) {
        super();
        this.login=login;
        this.description=description;
        this.accepted = accepted;

    }


    public void incrVotePour() {
        votesPour++;
    }
    public void incrVoteContr() {
        votesContre++;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getVotesPour() {
        return votesPour;
    }
    public void setVotesPour(int votesPour) {
        this.votesPour = votesPour;
    }
    public int getVotesContre() {
        return votesContre;
    }
    public void setVotesContre(int votesContre) {
        this.votesContre = votesContre;
    }
    public Boolean getAccepted() {
        return accepted;
    }
    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }


    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}
