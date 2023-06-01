package projet.ejb.dao;

import java.util.List;

import projet.ejb.data.Personne;
import projet.ejb.data.Ouvrage;


public interface IDaoOuvrage {

	void insererPourPersonne(Ouvrage ouvrage);

	void modifierPourPersonne(Ouvrage ouvrage);

	void supprimerPourPersonne(int idOuvrage);

	List<Ouvrage> listerPourPersonne( Ouvrage ouvrage );

}
