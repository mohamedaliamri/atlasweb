package atlasweb.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

public class Bracelet {

	@Id
	@GeneratedValue
	@Column(name = "BRACELET_ID")
	private Integer id;

	@Column(name = "prenom")
	private String prenom;

	@Column(name = "dateNaissance")
	private Date dateNaissance;
	
	@Column(name ="typeBracelet")
	private String typeBracelet;
	
	@Column(name ="prix")
	private double prix;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "BRACELET_JEUX", 
             joinColumns = { 
            		 @JoinColumn(name = "BRACELET_ID") }, 
             inverseJoinColumns = {
            		 @JoinColumn(name = "JEUX_ID") })
	private ArrayList<Jeux> subjects = new ArrayList<>();


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public Date getDateNaissance() {
		return dateNaissance;
	}


	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	public String getTypeBracelet() {
		return typeBracelet;
	}


	public void setTypeBracelet(String typeBracelet) {
		this.typeBracelet = typeBracelet;
	}


	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}


	public ArrayList<Jeux> getSubjects() {
		return subjects;
	}


	public void setSubjects(ArrayList<Jeux> subjects) {
		this.subjects = subjects;
	}

	
	
}
