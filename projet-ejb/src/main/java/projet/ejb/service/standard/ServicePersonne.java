package projet.ejb.service.standard;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import projet.commun.dto.DtoPersonne;
import projet.commun.dto.DtoOuvrage;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServicePersonne;
import projet.ejb.dao.IDaoPersonne;
import projet.ejb.data.Amitie;
import projet.ejb.data.AmitiePK;
import projet.ejb.data.Personne;
import projet.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServicePersonne implements IServicePersonne {

	// Champs
	@Inject
	private IMapperEjb mapper;
	@Inject
	private IDaoPersonne daoPersonne;

	// Actions

	@Override
	public int inserer(DtoPersonne dtoPersonne) throws ExceptionValidation {
		verifierValiditeDonnees(dtoPersonne);
		int id = daoPersonne.inserer(mapper.map(dtoPersonne));
		return id;
	}

	@Override
	public void modifier(DtoPersonne dtoPersonne) throws ExceptionValidation {
		verifierValiditeDonnees(dtoPersonne);
		daoPersonne.modifier(mapper.map(dtoPersonne));
	}

	@Override
	public void supprimer(int idPersonne) throws ExceptionValidation {
		daoPersonne.supprimer(idPersonne);
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public DtoPersonne retrouver(int idPersonne) {
		Personne personne = daoPersonne.retrouver(idPersonne);
		return mapper.map(personne);

	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoPersonne> listerTout() {
		List<DtoPersonne> liste = new ArrayList<>();
		for (Personne personne : daoPersonne.listerTout()) {
			liste.add( mapper.map(personne) );
		}
		return liste;
		
	}

	// Méthodes auxiliaires

	private void verifierValiditeDonnees(DtoPersonne dtoPersonne) throws ExceptionValidation {

		StringBuilder message = new StringBuilder();

		if (dtoPersonne.getNom() == null || dtoPersonne.getNom().isEmpty()) {
			message.append("\nLe nom est absent.");
		} else if (dtoPersonne.getNom().length() > 25) {
			message.append("\nLe nom est trop long.");
		}

		if (dtoPersonne.getPrenom() == null || dtoPersonne.getPrenom().isEmpty()) {
			message.append("\nLe prénom est absent.");
		} else if (dtoPersonne.getPrenom().length() > 25) {
			message.append("\nLe prénom est trop long.");
		}

		for (DtoOuvrage ouvrage : dtoPersonne.getOuvrages()) {
			if (ouvrage.getLibelle() == null || ouvrage.getLibelle().isEmpty()) {
				message.append("\nLlibellé absent pour le téléphone : " + ouvrage.getNumero());
			} else if (ouvrage.getLibelle().length() > 25) {
				message.append("\nLe libellé du téléphone est trop long : " + ouvrage.getLibelle());
			}

			if (ouvrage.getNumero() == null || ouvrage.getNumero().isEmpty()) {
				message.append("\nNuméro absent pour le téléphone : " + ouvrage.getLibelle());
			} else if (ouvrage.getNumero().length() > 25) {
				message.append("\nLe numéro du téléphone est trop long : " + ouvrage.getNumero());
			}
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}
	}

	@Override
	public List<DtoPersonne> searchByNameOrSurname(String searchText,DtoPersonne personne) {
		List<DtoPersonne> liste = new ArrayList<>();
		for (Personne person : daoPersonne.searchByNameOrSurname(searchText, mapper.map(personne))) {
			liste.add( mapper.map(person) );
		}
		return liste;
	}

	@Override
	public int ajouterAmi(int idAmi,int idPersonne) throws ExceptionValidation{
		Amitie amitie=new Amitie(idPersonne,idAmi,false);
		int id = daoPersonne.insererAmitie(amitie);
		return id;
		
	}

	@Override
	public List<DtoPersonne> listerInvitations(int idPersonne) {
		List<DtoPersonne> liste = new ArrayList<>();
		for (Personne personne : daoPersonne.listerInvitations(idPersonne)) {
			liste.add( mapper.map(personne) );
		}
		return liste;
	}

	@Override
	public List<DtoPersonne> listerAmis(int idPersonne) {
		List<DtoPersonne> liste = new ArrayList<>();
		for (Personne personne : daoPersonne.listerAmis(idPersonne)) {
			liste.add( mapper.map(personne) );
		}
		return liste;
	}

	@Override
	public void validateInvitation(int idAmi, int idPersonne) {
		Amitie amitie=new Amitie(idAmi,idPersonne,true);
		daoPersonne.AcceptFriendShip(amitie);
	}

}
