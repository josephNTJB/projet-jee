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
	public void supprimerPourPersonne(int idOuvrage) {
		em.remove( retrouver(idOuvrage) );
		
	}
	

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Ouvrage> listerPourPersonne(int idPersonne) {
		em.clear();
		var jpql = "SELECT c FROM Ouvrage c ORDER BY c.libelle WHERE id_Personne =:idPersonne";
		var query = em.createQuery( jpql, Ouvrage.class );
		return query.getResultList();
	}
	
	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Ouvrage> listerTout() {
		em.clear();
		var jpql = "SELECT c FROM Ouvrage c ORDER BY c.libelle";
		var query = em.createQuery( jpql, Ouvrage.class );
		return query.getResultList();
	}

	
	
	
	
}
