package projet.ejb.data;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


@Entity
@Table( name ="identifiant")
public class Compte  {

	// Champs

	@Id
	@GeneratedValue( strategy = IDENTITY)
	@Column( name = "id_identifiant")
	private int			id;
	
	@Column( name = "username")
	private String		pseudo;
	
	@Column( name = "password")
	private String		motDePasse;
	

	//private String		email;

	@ElementCollection( fetch = EAGER )
	@CollectionTable( name = "role", joinColumns = @JoinColumn( name = "idCompte" ) )
	@Column( name = "role")
	private List<String> roles = new ArrayList<>();	
	
	
	// Constructeurs
	
	public Compte() {
	}

	public Compte(int id, String pseudo, String motDePasse) {
		this.id = id;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		//this.email = pseudo;
	}
	
		
	// Getters & setters
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	/*public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}*/

	public List<String> getRoles() {
		return roles;
	}
	
	public String getListeRoles() {
		
		String format="";
		
		for (String role : roles)
		{
			format+= role.toLowerCase()+" ";
		}
		return format;
	}
	/*

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}*/

    
	// equals() et hashcode()

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
