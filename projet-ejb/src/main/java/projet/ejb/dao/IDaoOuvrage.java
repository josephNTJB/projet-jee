package projet.ejb.dao;

import java.util.List;

import projet.ejb.data.Personne;
import projet.ejb.data.Ouvrage;


public interface IDaoOuvrage {

	void insererPourPersonne(Personne personne);

	void modifierPourPersonne(Personne personne);

	void supprimerPourPersonne(int idPersonne);

	List<Ouvrage> listerPourPersonne( Personne personne );

}
