package projet.ejb.dao;

import java.util.List;

import projet.ejb.data.Personne;


public interface IDaoPersonne {

	int			inserer( Personne personne );

	void 		modifier( Personne personne );

	void 		supprimer( int idPersonne );

	Personne 	retrouver( int idPersonne );

	List<Personne> listerTout();
    
    int 		compterPourCategorie( int idCategorie );

}
