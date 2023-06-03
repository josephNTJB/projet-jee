package projet.ejb.data;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Embeddable
public class AmitiePK implements java.io.Serializable{
    
	
    // Champs
    private int      	idPersonne;
    private int     	idAmi;
	// Constructeurs

	public AmitiePK() {
		super();
	}
    
    public AmitiePK(int idP, int idA) {
		super();
		this.idPersonne = idP;
	}

    // Getters & setters
    
	public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	public int getIdAmi() {
		return idAmi;
	}

	public void setIdAmi(int idAmi) {
		this.idAmi = idAmi;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAmi, idPersonne);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AmitiePK other = (AmitiePK) obj;
		return idAmi == other.idAmi && idPersonne == other.idPersonne;
	}
    
    
	// hashcode() & equals()
    

    
}
