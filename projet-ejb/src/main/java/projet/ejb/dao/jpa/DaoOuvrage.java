package projet.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projet.ejb.dao.IDaoOuvrage;
import projet.ejb.data.Emprunt;
import projet.ejb.data.Ouvrage;


@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoOuvrage implements IDaoOuvrage {

	
	// Champs

	@PersistenceContext
	private EntityManager	em;
	
	
	// Actions

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public Ouvrage retrouver(int idOuvrage) {
		return em.find( Ouvrage.class, idOuvrage );
	}

	@Override
	public int insererPourPersonne(Ouvrage ouvrage) {
		em.persist(ouvrage);
		em.flush();
		return ouvrage.getId();
		
	}

	@Override
	public void modifierPourPersonne(Ouvrage ouvrage) {
		em.merge( ouvrage );
		
	}

	@Override
	public void supprimerPourPersonne(Ouvrage ouvrage) {
		em.remove(em.getReference(Ouvrage.class, ouvrage.getId()));
		
	}
	

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Ouvrage> listerPourPersonne(int idPersonne) {
		em.clear();
		var jpql = "SELECT o FROM Ouvrage o  WHERE o.personne.id =:idPersonne ORDER BY o.nom";
		var query = em.createQuery( jpql, Ouvrage.class );
		query.setParameter("idPersonne", idPersonne);
		return query.getResultList();
	}
	
	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Ouvrage> listerTout() {
		em.clear();
		var jpql = "SELECT o FROM Ouvrage o ORDER BY o.nom";
		var query = em.createQuery( jpql, Ouvrage.class );
		return query.getResultList();
	}

	@Override
	public List<Ouvrage> listerPourAmis(int idPersonne) {
		em.clear();
		var jpql = "SELECT o FROM Ouvrage o  WHERE o.personne.id IN (SELECT p.id FROM Personne p WHERE p.id IN ( SELECT CASE WHEN a.idpersonne = :idPerson THEN a.idami ELSE a.idpersonne END FROM Amitie a WHERE (a.idpersonne = :idPerson OR a.idami = :idPerson) AND a.isValid = true)) ORDER BY o.nom";
		var query = em.createQuery( jpql, Ouvrage.class );
		query.setParameter("idPerson", idPersonne);
		return query.getResultList();
	}

	@Override
	public int insererEmprunt(int idOuvrage, int idPersonne) {
		Emprunt emp=new Emprunt(idOuvrage,idPersonne);
		em.persist(emp);
		em.flush();
		return 0;
	}

	@Override
	public List<Ouvrage> listerPourEmprunts(int idPersonne) {
		em.clear();
		var jpql = "SELECT o FROM Ouvrage o  WHERE o.id IN (SELECT e.idouvrage FROM Emprunt e WHERE e.idpersonne = :idpersonne )";
		var query = em.createQuery( jpql, Ouvrage.class );
		query.setParameter("idpersonne", idPersonne);
		return query.getResultList();
	}

	
	
	
	
}
