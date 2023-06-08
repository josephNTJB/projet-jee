package projet.ejb.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "emprunt" )
@IdClass(EmpruntPK.class)
public class Emprunt {
    
	
    // Champs
    @Id
    @Column(name = "id_personne")
    private int     idpersonne;
    @Id
    @Column(name = "id_ouvrage")
    private int    	idouvrage;

	
	// Constructeurs

	public Emprunt() {
	}
    
    public Emprunt(int idA, int idP) {
		this.idpersonne = idP;
		this.idouvrage = idA;
	}

    // Getters & setters

	public int getIdpersonne() {
		return idpersonne;
	}

	public void setIdpersonne(int idpersonne) {
		this.idpersonne = idpersonne;
	}

	public int getIdouvrage() {
		return idouvrage;
	}

	public void setIdouvrage(int idouvrage) {
		this.idouvrage = idouvrage;
	}

	

}
