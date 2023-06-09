package projet.ejb.dao;

import java.util.List;

import projet.ejb.data.Personne;
import projet.commun.dto.DtoOuvrage;
import projet.ejb.data.Ouvrage;


public interface IDaoOuvrage {

	int insererPourPersonne(Ouvrage ouvrage);

	void modifierPourPersonne(Ouvrage ouvrage);

	Ouvrage retrouver(int idOuvrage);

	List<Ouvrage> listerTout();

	List<Ouvrage> listerPourPersonne(int idPersonne);

	void supprimerPourPersonne(Ouvrage ouvrage);

	List<Ouvrage> listerPourAmis(int idPersonne);

	int insererEmprunt(int idOuvrage, int idPersonne);

	List<Ouvrage> listerPourEmprunts(int idPersonne);

	List<Ouvrage> searchByNameOrAutor(String searchText, Ouvrage ouvrage);

}
