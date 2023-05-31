package projet.commun.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class DtoPersonne implements Serializable {
	
	
	// Champs
	
	private int				id;
	
	private String			nom;
	
	private String			prenom;
	
	private DtoCategorie	categorie;
	
	private List<DtoOuvrage>	ouvrages = new ArrayList<>();
	
	
	// Constructeurs
	
	public DtoPersonne() {
	}

	public DtoPersonne(int id, String nom, String prenom, DtoCategorie categorie ) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.categorie = categorie;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public DtoCategorie getCategorie() {
		return categorie;
	}

	public void setCategorie(DtoCategorie categorie) {
		this.categorie = categorie;
	}

	public List<DtoOuvrage> getOuvrages() {
		return ouvrages;
	}

	public void setTelephones(List<DtoOuvrage> ouvrages) {
		this.ouvrages = ouvrages;
	}

}
