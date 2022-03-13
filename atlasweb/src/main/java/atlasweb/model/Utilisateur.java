package atlasweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

	@Id
	@Column(name = "phonenumber")
	private String phoneNumber;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@ManyToOne
	@JoinColumn(name = "role_name")
	private Role role;

	public Utilisateur() {
		super();
	}

	public Utilisateur(String phoneNumber, String name, String code, Role role) {
		super();
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.code = code;
		this.role = role;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
