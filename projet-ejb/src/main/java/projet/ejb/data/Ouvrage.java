package projet.ejb.data;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "ouvrage" )
public class Ouvrage {

	
	// Champs

	@Id
	@GeneratedValue( strategy = IDENTITY )
	@Column( name = "id_ouvrage" )
	private int				id;
	
	@ManyToOne
	@JoinColumn( name = "id_personne" )
	private Personne		personne;

	@Column( name = "auteur" )
	private String			auteur;

	@Column( name = "nom" )
	private String			nom;
	
	
	// Constructeurs
	
	public Ouvrage() {
	}
	
	public Ouvrage(int id, Personne personne, String auteur, String nom) {
		this.id = id;
		this.personne = personne;
		this.auteur = auteur;
		this.nom = nom;
	}


	// Getters & setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	// hashcode() et equals()
	
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
		Ouvrage other = (Ouvrage) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
