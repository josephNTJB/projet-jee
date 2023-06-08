package projet.jsf.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@SuppressWarnings("serial")
public class Ouvrage implements Serializable {


	// Champs
	
	private Integer			id;
	
	@NotBlank( message = "L'auteur doit etre renseigné")
	@Size(max=25, message = "Valeur trop longue pour le nom : 25 car. maxi" )
	private String			auteur;

	@NotBlank( message = "Le nom doit etre renseigné")
	@Size(max=25, message = "Valeur trop longue pour le prénom : 25 car. maxi" )
	private String			nom;

	@NotNull( message = "La catégorie est obligatoire")
	private Personne		personne;

	private List<Categorie>	categories = new ArrayList<>();
	
	@NotNull( message = "La couverture est obligatoire")
	private String			couverture;
	@NotNull( message = "La fichier est obligatoire")
	private String			fichier;
	

	
	// Constructeurs
	
	public Ouvrage() {
	}

	public Ouvrage(int id, Personne personne, String auteur, String nom,String fichier,String couverture) {
		this.id = id;
		this.personne = personne;
		this.auteur = auteur;
		this.nom = nom;
		this.fichier = fichier;
		this.couverture = couverture;
	}
	
	
	// Getters & setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public String getAuteur() {
		return auteur ;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public List<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}
	
	
	public String getCouverture() {
		return couverture;
	}

	public void setCouverture(String couverture) {
		this.couverture = couverture;
	}

	public String getFichier() {
		return fichier;
	}

	public void setFichier(String fichier) {
		this.fichier = fichier;
	}

	public String categorieToString()
	{
		String format="";
		
		
		for (Categorie c : categories)
		{
			if (categories.size() ==1)
				format= c.getLibelle();
			else
				format+="," + c.getLibelle();
		}
		
		return format;
		
	}
	
	// hashCode() & equals()

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		var other = (Ouvrage) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Ouvrage [id=" + id + ", auteur=" + auteur + ", nom=" + nom + ", personne=" + personne + ", categories="
				+ categories + "]";
	}
	

}
