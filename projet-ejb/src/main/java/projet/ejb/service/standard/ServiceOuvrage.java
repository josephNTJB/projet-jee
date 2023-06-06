package projet.ejb.service.standard;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import projet.commun.dto.DtoOuvrage;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceOuvrage;
import projet.ejb.dao.IDaoOuvrage;
import projet.ejb.data.Ouvrage;
import projet.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServiceOuvrage implements IServiceOuvrage{

	// Champs
	@Inject
	private IMapperEjb mapper;
	@Inject
	private IDaoOuvrage daoOuvrage;
	
	private Ouvrage courant;

	// Actions

	@Override
	public int insererPourPersonne(DtoOuvrage dtoOuvrage) throws ExceptionValidation {
		verifierValiditeDonnees(dtoOuvrage);
		int id = daoOuvrage.insererPourPersonne(mapper.map(dtoOuvrage));
		return id;
	}

	@Override
	public void modifierPourPersonne(DtoOuvrage dtoOuvrage) throws ExceptionValidation {
		verifierValiditeDonnees(dtoOuvrage);
		daoOuvrage.modifierPourPersonne(mapper.map(dtoOuvrage));
	}

	@Override
	public void supprimer() throws ExceptionValidation {
		daoOuvrage.supprimerPourPersonne(courant.getId());
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public DtoOuvrage retrouver(int idOuvrage) {
		return mapper.map(daoOuvrage.retrouver(idOuvrage));
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoOuvrage> listerTout() {
		List<DtoOuvrage> liste = new ArrayList<>();
		for (Ouvrage ouvrage : daoOuvrage.listerTout()) {
			liste.add(mapper.map(ouvrage));
		}
		return liste;
	}

	// Méthodes auxiliaires

	private void verifierValiditeDonnees(DtoOuvrage dtoOuvrage) throws ExceptionValidation {

		StringBuilder message = new StringBuilder();

		if (dtoOuvrage.getNom() == null || dtoOuvrage.getNom().isEmpty()) {
			message.append("\nLe libellé est absent.");
		} else if (dtoOuvrage.getNom().length() < 3) {
			message.append("\nLe libellé est trop court.");
		} else if (dtoOuvrage.getNom().length() > 25) {
			message.append("\nLe libellé est trop long.");
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}
	}

	@Override
	public void supprimerPourPersonne(int idOuvrage) throws ExceptionValidation {
		daoOuvrage.supprimerPourPersonne(idOuvrage);
		
	}

	@Override
	public List<DtoOuvrage> listerPourPersonne(int idPersonne) {
		List<DtoOuvrage> liste = new ArrayList<>();
		for (Ouvrage ouvrage : daoOuvrage.listerPourPersonne(idPersonne)) {
			liste.add(mapper.map(ouvrage));
		}
		return liste;
	}

}
