package projet.ejb.data;

import java.util.Objects;


@SuppressWarnings("serial")
public class EmpruntPK implements java.io.Serializable{
    
	
    // Champs
    private int      	idpersonne;
    private int     	idouvrage;
	// Constructeurs

	public EmpruntPK() {

	}
    
    public EmpruntPK(int idP, int idA) {
		this.idpersonne = idP;
		this.idouvrage = idA;
	}

    // Getters & setters
    
	public int getIdPersonne() {
		return idpersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idpersonne = idPersonne;
	}

	public int getIdOuvrage() {
		return idouvrage;
	}

	public void setIdOuvrage(int idOuvrage) {
		this.idouvrage = idOuvrage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idouvrage, idpersonne);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpruntPK other = (EmpruntPK) obj;
		return idouvrage == other.idouvrage && idpersonne == other.idpersonne;
	}
    
    
	// hashcode() & equals()
    

    
}
