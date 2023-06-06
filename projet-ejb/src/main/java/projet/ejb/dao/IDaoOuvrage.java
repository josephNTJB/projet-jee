package projet.ejb.dao;

import java.util.List;

import projet.ejb.data.Personne;
import projet.ejb.data.Ouvrage;


public interface IDaoOuvrage {

	int insererPourPersonne(Ouvrage ouvrage);

	void modifierPourPersonne(Ouvrage ouvrage);

	void supprimerPourPersonne(int idOuvrage);

	Ouvrage retrouver(int idOuvrage);

	List<Ouvrage> listerTout();

	List<Ouvrage> listerPourPersonne(int idPersonne);

}
