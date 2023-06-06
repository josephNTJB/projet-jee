package projet.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
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
import projet.jsf.util.CompteActif;
import projet.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@ViewScoped
@Named
public class ModelPersonne implements Serializable {

	
	// Champs
	
	private List<Personne>		liste;
	private List<Personne>		listeAmis;
	
	private Personne			courant;
	
	private String searchText;

	@EJB
	private IServicePersonne	servicePersonne;

	@Inject
	private IMapper				mapper;
	@Inject
	private ModelCompte modelCompte;
	@Inject
	CompteActif compteActif;

	
	// Getters 
	
	public List<Personne> getListe() {
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
		if ( courant == null ) {
			DtoPersonne dto = servicePersonne.retrouver( compteActif.getId() ); 
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
		    actualiserCourant();
			liste = new ArrayList<>();
			for ( DtoPersonne dto : servicePersonne.searchByNameOrSurname(searchText,mapper.map(courant))) {
				liste.add( mapper.map( dto ) );
			}

	}
	//mettre à jour des personnes à inviter
		public void searchForFriendShip() throws ExceptionValidation {
			actualiserCourant();
			search();
			getFriends();
			List<Personne> suppr=new ArrayList<Personne>();
			List<Personne> demands=getDemands();
			for(Personne p: liste) {
				if(listeAmis.contains(p)) {
					suppr.add(p);
				}
			}
			for(Personne p: liste) {
				if(demands.contains(p)) {
					suppr.add(p);
				}
			}
			liste.removeAll(suppr);
		}
	//inviter un ami
	public void invite(int idAmi) throws ExceptionValidation {
		servicePersonne.ajouterAmi(idAmi,courant.getId());
    }
	//valider une invitation
		public void validInvitation(int idAmi) throws ExceptionValidation {
			servicePersonne.validateInvitation(idAmi,courant.getId());
	    }
	//recevoir la liste d'invitations
	public List<Personne> getInvitations() throws ExceptionValidation {
		actualiserCourant();
		List<Personne> liste = new ArrayList<Personne>();

			for ( DtoPersonne dto : servicePersonne.listerInvitations(courant.getId()) ) {
				liste.add( mapper.map( dto ) );
			}
		return liste;
	}
	public List<Personne> getDemands() throws ExceptionValidation {
		actualiserCourant();
		List<Personne> liste = new ArrayList<Personne>();
			for ( DtoPersonne dto : servicePersonne.listerDemandes(courant.getId()) ) {
				liste.add( mapper.map( dto ) );
			}
		return liste;
	}
	//recevoir la liste d'amis
		public void getFriends() throws ExceptionValidation {
			if (listeAmis == null ) {
				listeAmis = new ArrayList<>();
			}
			for ( DtoPersonne dto : servicePersonne.listerAmis(courant.getId()) ) {
				if(!listeAmis.contains(mapper.map( dto ))) {
				listeAmis.add( mapper.map( dto ) );}
			}
		}
    //supprimer une amitié -------mechant mechant :) -------------
		public void brokeUp(int idAmi) throws ExceptionValidation {
			try {
				servicePersonne.deleteFriend(idAmi,courant.getId());
				getFriends();
				UtilJsf.messageInfo( ":( un ami en moins " );
			} catch (ExceptionValidation e) {
				UtilJsf.messageError( e ); 
			}
	    }
		 //supprimer une amitié -------noxious :) -------------
		public void forget(int idAmi) throws ExceptionValidation {
			try {
				servicePersonne.cancelDemand(idAmi,courant.getId());
				getFriends();
				UtilJsf.messageInfo( "se sera la bonne une prochaine fois" );
			} catch (ExceptionValidation e) {
				UtilJsf.messageError( e ); 
			}
	    }
	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public List<Personne> getListeAmis() {
		try {
			actualiserCourant();
			getFriends();
		} catch (ExceptionValidation e) {
			e.printStackTrace();
		}
		return listeAmis;
	}

	public void setListeAmis(List<Personne> listeAmis) {
		this.listeAmis = listeAmis;
	}
	
}
