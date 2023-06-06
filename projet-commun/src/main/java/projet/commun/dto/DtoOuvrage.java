package projet.commun.dto;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")
public class DtoOuvrage implements Serializable {

	
	// Champs

	private int				id;

	private String			nom;

	private String			auteur;
	
	private List<DtoCategorie> categories;	
	
	// Constructeurs
	
	public DtoOuvrage() {
	}
	
	public DtoOuvrage(int id, String nom, String auteur) {
		this.id = id;
		this.nom = nom;
		this.auteur = auteur;
	}


	// Getters & setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public List<DtoCategorie> getCategories() {
		return categories;
	}

	public void setCategories(List<DtoCategorie> categories) {
		this.categories = categories;
	}
}
