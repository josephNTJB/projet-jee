package projet.ejb.data;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "role" )
public class Role {
    
	
    // Champs
    
	@Id
	@Column( name = "IdCompte" )
    private int         	id;
    
	@Column( name = "Role" )
    private String      	libelle;
	
	// Constructeurs

	public Role() {
		super();
	}
    
    public Role(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
    
    
    // Getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    
    // soString()
    @Override
    public String toString() {
    	return libelle;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id, libelle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return id == other.id && Objects.equals(libelle, other.libelle);
	}

    
	// hashcode() & equals()

    
}
