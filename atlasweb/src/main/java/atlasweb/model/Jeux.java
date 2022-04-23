package atlasweb.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Jeux {

	private String nomJeux;
	private String couleur;
	private double prix;
	
	@ManyToOne
	@JoinColumn(name = "bracelet_id")
	private Bracelet barcelet;
	
}
