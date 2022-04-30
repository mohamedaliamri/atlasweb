package atlasweb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "bracelet")
public class Bracelet {

	@Id
	@GeneratedValue
	@Column(name = "BRACELET_ID")
	private Integer id;

	@Column(name = "prenom")
	private String prenom;
	
	@ManyToOne
	@JoinColumn(name = "phonenumber")
	private Utilisateur utilisateur;

	@Column(name = "dateNaissance")
	private Date dateNaissance;
	
	@Column(name ="typeBracelet")
	private String typeBracelet;
	
	@Column(name ="prix")
	private double prix;
	
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="bracelet_jeux",
	joinColumns=@JoinColumn(name="BRACELET_ID"),
	inverseJoinColumns=@JoinColumn(name="jeux_id"))
	private List<Jeux> jeux = new ArrayList<Jeux>();


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


	public List<Jeux> getJeux() {
		return jeux;
	}


	public void setJeux(List<Jeux> jeux) {
		this.jeux = jeux;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}




	
	
}
