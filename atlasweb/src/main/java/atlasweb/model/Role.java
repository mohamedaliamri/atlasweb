package atlasweb.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="role")
public class Role {
	
	@Id
	@Column(name = "name")
	private String name;
	
	@OneToMany(targetEntity = Utilisateur.class, mappedBy = "role")
	private List<Utilisateur> utilisateurs;

	
	public Role() {
		super();
	}


	public Role(String name) {
		super();
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
