package projet.ejb.data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "etre_ami" )
public class Amitie {
    
	
    // Champs

	@EmbeddedId
	private AmitiePK clePrimaire;
	public AmitiePK getClePrimaire() {
		return clePrimaire;
	}

	public void setClePrimaire(AmitiePK clePrimaire) {
		this.clePrimaire = clePrimaire;
	}

	@Column( name = "is_validate" )
    private boolean     isValid;
	
	// Constructeurs

	public Amitie() {
		super();
	}
    
    public Amitie(int idP, int idA, boolean valid) {
		super();
		this.clePrimaire=new AmitiePK(idP,idA);
		this.isValid=valid;
	}

    // Getters & setters

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}
