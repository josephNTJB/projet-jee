package projet.commun.service;

import java.util.List;

import projet.commun.dto.DtoOuvrage;
import projet.commun.exception.ExceptionValidation;



public interface IServiceOuvrage {
	
	int insererPourPersonne(DtoOuvrage dtoOuvrage) throws ExceptionValidation;


	void supprimerPourPersonne(DtoOuvrage ouvrage) throws ExceptionValidation;
	
	List<DtoOuvrage> listerTout();

	List<DtoOuvrage> listerPourPersonne( int idPersonne);
	
	DtoOuvrage  retrouver(int idOuvrage);

	void supprimer() throws ExceptionValidation;


	void modifierPourPersonne(DtoOuvrage dtoOuvrage) throws ExceptionValidation;


	List<DtoOuvrage> listerPourAmis(int id);


	int emprunter(int id, int id2);


	List<DtoOuvrage> listerPourEmprunts(int id);


	List<DtoOuvrage> searchByNameOrAutor(String searchText, DtoOuvrage map);


	

}
