package projet.ejb.dao;

import java.util.List;

import projet.ejb.data.Amitie;
import projet.ejb.data.Personne;


public interface IDaoPersonne {

	int			inserer( Personne personne );

	void 		modifier( Personne personne );

	void 		supprimer( int idPersonne );

	Personne 	retrouver( int idPersonne );

	List<Personne> listerTout();
    
    int 		compterPourCategorie( int idCategorie );

	List<Personne> searchByNameOrSurname(String searchText, Personne personneCourante);

	int insererAmitie(Amitie amitie);

	List<Personne> listerInvitations(int idPersonne);

	List<Personne> listerAmis(int idPersonne);

	void AcceptFriendShip(Amitie amitie);

	void DeleteFriendShip(Amitie amitie);

	List<Personne> listerDemandes(int id);

	Amitie findAmitie(int idPerson, int idAmi);

	Amitie findDemand(int idPersonne, int idAmi);

}
