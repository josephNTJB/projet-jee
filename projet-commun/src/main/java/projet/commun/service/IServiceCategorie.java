package projet.commun.service;

import java.util.List;

import projet.commun.dto.DtoCategorie;
import projet.commun.exception.ExceptionValidation;


public interface IServiceCategorie {
	
	int		inserer( DtoCategorie dtoCategorie ) throws ExceptionValidation;

	void	modifier( DtoCategorie dtoCategorie ) throws ExceptionValidation;

	void	supprimer( int idCategorie ) throws ExceptionValidation;

	DtoCategorie	retrouver( int idCategorie );

	List<DtoCategorie>	listerTout();

}
