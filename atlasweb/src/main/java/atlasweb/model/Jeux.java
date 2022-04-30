package atlasweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jeux")
public class Jeux {
	
	@Id
    @GeneratedValue
    @Column(name = "JEUX_ID")
    private long id;
 
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "PRIX")
    private String prix;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return name + "  ";
	}
	
	
    
    

}
