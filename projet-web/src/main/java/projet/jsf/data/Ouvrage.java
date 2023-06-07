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

	
	// Constructeurs
	
	public Ouvrage() {
	}

	public Ouvrage(Integer id, String nom, String auteur, Personne personne) {
		super();
		this.id = id;
		this.nom = nom;
		this.auteur = auteur;
		this.personne = personne;
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
	

}
