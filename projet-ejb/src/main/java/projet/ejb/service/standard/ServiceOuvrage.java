package projet.ejb.service.standard;

import java.util.List;

import projet.commun.dto.DtoOuvrage;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceOuvrage;

public class ServiceOuvrage implements IServiceOuvrage {

	@Override
	public int insererPourPersonne(DtoOuvrage dtoOuvrage) throws ExceptionValidation {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void modifierPourPersonne(DtoOuvrage dtoOuvrage) throws ExceptionValidation {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerPourPersonne(int idOuvrage) throws ExceptionValidation {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DtoOuvrage> listerTout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DtoOuvrage> listerPourPersonne(DtoOuvrage dtoOuvrage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtoOuvrage retrouver(int idOuvrage) {
		// TODO Auto-generated method stub
		return null;
	}

}
