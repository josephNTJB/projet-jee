package projet.ejb.data;

import java.util.Objects;


@SuppressWarnings("serial")
public class AmitiePK implements java.io.Serializable{
    
	
    // Champs
    private int      	idpersonne;
    private int     	idami;
	// Constructeurs

	public AmitiePK() {

	}
    
    public AmitiePK(int idP, int idA) {
		this.idpersonne = idP;
		this.idami = idA;
	}

    // Getters & setters
    
	public int getIdPersonne() {
		return idpersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idpersonne = idPersonne;
	}

	public int getIdAmi() {
		return idami;
	}

	public void setIdAmi(int idAmi) {
		this.idami = idAmi;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idami, idpersonne);
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
		return idami == other.idami && idpersonne == other.idpersonne;
	}
    
    
	// hashcode() & equals()
    

    
}
