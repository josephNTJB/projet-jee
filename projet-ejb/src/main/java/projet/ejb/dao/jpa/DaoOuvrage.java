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
	public int inserer(Ouvrage ouvrage) {

	}

	@Override
	public void modifier(Ouvrage ouvrage) {
		em.merge( ouvrage );
	}

	@Override
	public void supprimer(int idOuvrage) {
		em.remove( retrouver(idOuvrage) );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public Ouvrage retrouver(int idOuvrage) {
		return em.find( Ouvrage.class, idOuvrage );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Ouvrage> listerTout() {
		em.clear();
		var jpql = "SELECT c FROM Ouvrage c ORDER BY c.libelle";
		var query = em.createQuery( jpql, Ouvrage.class );
		return query.getResultList();
	}

	@Override
	public int insererPourPersonne(Ouvrage ouvrage) {
		em.persist(ouvrage);
		em.flush();
		return ouvrage.getId();
		
	}

	@Override
	public void modifierPourPersonne(Ouvrage ouvrage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerPourPersonne(int idOuvrage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Ouvrage> listerPourPersonne(Ouvrage ouvrage) {
		// TODO Auto-generated method stub
		return null;
	}
	
}