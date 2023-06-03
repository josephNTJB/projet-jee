package projet.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoPersonne;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServicePersonne;
import projet.jsf.data.Personne;
import projet.jsf.data.Ouvrage;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@RequestScoped
@Named
public class ModelPersonne implements Serializable {

	
	// Champs
	
	private List<Personne>		liste;
	private List<Personne>		listeAmis;
	
	private Personne			courant;
	private int			idAmi;
	
	private String searchText;
	
	private List<Personne> searchResults;
	
	@EJB
	private IServicePersonne	servicePersonne;

	@Inject
	private IMapper				mapper;

	
	// Getters 
	
	public List<Personne> getListe() {
		if ( liste == null ) {
			liste = new ArrayList<>();
			for ( DtoPersonne dto : servicePersonne.listerTout() ) {
				liste.add( mapper.map( dto ) );
			}
		}
		return liste;
	}

	public Personne getCourant() {
		if ( courant == null ) {
			courant = new Personne();
		}
		return courant;
	}
	
	
	// Initialisaitons
	
	public String actualiserCourant() {
		if ( courant != null ) {
			DtoPersonne dto = servicePersonne.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "La personne demandée n'existe pas" );
				return "liste";
			} else {
				courant = mapper.map( dto );
			}
		}
		return null;
	}
	
	
	// Actions
	
	public String validerMiseAJour() {
		try {
			if ( courant.getId() == null) {
				servicePersonne.inserer( mapper.map(courant) );
			} else {
				servicePersonne.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}
	
	public String supprimer( Personne personne ) {
		try {
			servicePersonne.supprimer( personne.getId() );
			liste.remove(personne);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e ); 
		}
		return null;
	}
	
	
	public String ajouterOuvrage() {
		courant.getOuvrages().add( new Ouvrage() );
		return null;
	}
	
	
	public String supprimerOuvrage( Ouvrage ouvrage ) {
		for ( int i=0; i < courant.getOuvrages().size(); ++i ) {
			if ( courant.getOuvrages().get(i) == ouvrage ) {
				courant.getOuvrages().remove( i );
				break;
			}
		}
		return null;
	}
	//implementation de la recherche
	public void search() {
			liste = new ArrayList<>();
			for ( DtoPersonne dto : servicePersonne.searchByNameOrSurname(searchText,mapper.map(courant))) {
				liste.add( mapper.map( dto ) );
			}
		searchResults= liste;
		System.out.println(searchResults);
	}
	//inviter un ami
	public void invite() throws ExceptionValidation {
		servicePersonne.ajouterAmi(idAmi,courant.getId());
    }
	//recevoir la liste d'invitations
	public List<Personne> getInvitations() throws ExceptionValidation {
		if ( liste == null ) {
			liste = new ArrayList<>();
			for ( DtoPersonne dto : servicePersonne.listerInvitations(courant.getId()) ) {
				liste.add( mapper.map( dto ) );
			}
		}
		return liste;
	}
	//recevoir la liste d'amis
		public List<Personne> getFriends() throws ExceptionValidation {
			if (listeAmis == null ) {
				listeAmis = new ArrayList<>();
				for ( DtoPersonne dto : servicePersonne.listerAmis(courant.getId()) ) {
					listeAmis.add( mapper.map( dto ) );
				}
			}
			return listeAmis;
		}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public List<Personne> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<Personne> searchResults) {
		this.searchResults = searchResults;
	}

	public int getAmi() {
		return idAmi;
	}

	public void setAmi(int idAmi) {
		this.idAmi = idAmi;
	}

	public List<Personne> getListeAmis() {
		return listeAmis;
	}

	public void setListeAmis(List<Personne> listeAmis) {
		this.listeAmis = listeAmis;
	}
	
}
