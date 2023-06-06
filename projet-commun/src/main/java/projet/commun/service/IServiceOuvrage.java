package projet.commun.service;

import java.util.List;

import projet.commun.dto.DtoOuvrage;
import projet.commun.exception.ExceptionValidation;


public interface IServiceOuvrage {
	
	int insererPourPersonne(DtoOuvrage dtoOuvrage) throws ExceptionValidation;

	void modifierPourPersonne(DtoOuvrage dtoOuvrage) throws ExceptionValidation;

	void supprimerPourPersonne(int idOuvrage) throws ExceptionValidation;
	
	List<DtoOuvrage> listerTout();

	List<DtoOuvrage> listerPourPersonne( DtoOuvrage dtoOuvrage);
	
	DtoOuvrage  retrouver(int idOuvrage);


	

}
