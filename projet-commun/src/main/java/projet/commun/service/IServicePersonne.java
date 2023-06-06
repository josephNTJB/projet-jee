package projet.commun.service;

import java.util.List;

import projet.commun.dto.DtoPersonne;
import projet.commun.exception.ExceptionValidation;


public interface IServicePersonne {
	
	int				inserer( DtoPersonne dtoPersonne ) throws ExceptionValidation;
	
	void			modifier( DtoPersonne dtoPersonne ) throws ExceptionValidation;
	
	void			supprimer( int idPersonne ) throws ExceptionValidation;
	
	DtoPersonne 	retrouver( int idPersonne );
	
	List<DtoPersonne> listerTout();

	List<DtoPersonne> searchByNameOrSurname(String searchText, DtoPersonne personne);

	int ajouterAmi(int idAmi, int idPersonne) throws ExceptionValidation;

	List<DtoPersonne> listerInvitations(int idPersonne);

	List<DtoPersonne> listerAmis(int idPersonne);

	void validateInvitation(int idAmi, int idPersonne);

	void deleteFriend(int idAmi, int idPersonne) throws ExceptionValidation;;

	void cancelDemand(int idAmi, int idPersonne) throws ExceptionValidation;;
	

}
