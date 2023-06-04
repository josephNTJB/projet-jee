package projet.ejb.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "etre_ami" )
@IdClass(AmitiePK.class)
public class Amitie {
    
	
    // Champs
    @Id
    @Column(name = "id_personne")
    private int      	idpersonne;
    @Id
    @Column(name = "id_ami")
    private int     	idami;

	@Column( name = "is_validate" )
    private boolean     isValid;
//	public AmitiePK getClePrimaire() {
//		return clePrimaire;
//	}
//
//	public void setClePrimaire(AmitiePK clePrimaire) {
//		this.clePrimaire = clePrimaire;
//	}
	
	// Constructeurs

	public Amitie() {
	}
    
    public Amitie(int idP, int idA, boolean valid) {
		this.idpersonne = idP;
		this.idami = idA;
		this.isValid=valid;
	}

    // Getters & setters

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
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
	

}
