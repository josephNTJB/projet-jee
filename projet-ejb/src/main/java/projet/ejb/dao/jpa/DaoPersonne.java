package projet.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projet.ejb.dao.IDaoPersonne;
import projet.ejb.data.Amitie;
import projet.ejb.data.Personne;


@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoPersonne implements IDaoPersonne {
	
	// Champs
	
	@PersistenceContext
	private EntityManager	em;

	
	// Actions
	
	@Override
	public int inserer(Personne personne) {
		em.persist(personne);
		em.flush();
		return personne.getId();
	}
	@Override
	public int insererAmitie(Amitie amitie) {
		em.persist(amitie);
		em.flush();
		return amitie.getIdPersonne();
	}
	

	@Override
	public void modifier(Personne personne) {
		em.merge( personne );
	}

	@Override
	public void supprimer(int idPersonne) {
		em.remove( retrouver(idPersonne) );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public Personne retrouver(int idPersonne) {
		var graph = em.createEntityGraph( Personne.class );
		graph.addAttributeNodes( "ouvrages" );
		var props = new HashMap<String, Object>();
		props.put( "javax.persistence.loadgraph", graph );
		return em.find( Personne.class, idPersonne, props );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Personne> listerTout() {
		em.clear();
		var jpql = "SELECT p FROM Personne p ORDER BY p.nom, p.prenom";
		var query = em.createQuery( jpql, Personne.class );
		return query.getResultList();
	}


	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public int compterPourCategorie(int idCategorie) {
		var jpql = "SELECT COUNT(p) FROM Personne p WHERE p.categorie.id = :idCategorie";
		var query = em.createQuery( jpql, Long.class );
		query.setParameter( "idCategorie", idCategorie );
		return query.getSingleResult().intValue();
	}
	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Personne> searchByNameOrSurname(String searchText, Personne personneCourante) {
		  String jpql = "SELECT p FROM Personne p WHERE (p.nom LIKE :searchText OR p.prenom LIKE :searchText) AND p <> :personneCourante";
		  return em.createQuery(jpql, Personne.class)
		    .setParameter("searchText", "%" + searchText + "%")
		    .setParameter("personneCourante", personneCourante)
		    .getResultList();
		  
		}
	@Override
	public List<Personne> listerInvitations(int idPerson) {
		em.clear();
		var jpql = "SELECT p FROM Personne p WHERE p.id IN (SELECT a.idpersonne FROM Amitie a  WHERE a.idami = :idPerson AND a.isValid = false) ORDER BY p.nom, p.prenom";
		var query = em.createQuery( jpql, Personne.class );
		query.setParameter( "idPerson", idPerson );
		return query.getResultList();
	}
	@Override
	public List<Personne> listerAmis(int idPerson) {
		em.clear();
		var jpql = "SELECT p FROM Personne p WHERE p.id IN ( SELECT CASE WHEN a.idpersonne = :idPerson THEN a.idami ELSE a.idpersonne END FROM Amitie a WHERE (a.idpersonne = :idPerson OR a.idami = :idPerson) AND a.isValid = true) ORDER BY p.nom, p.prenom ";
		var query = em.createQuery( jpql, Personne.class );
		query.setParameter( "idPerson", idPerson );
		return query.getResultList();
	}
	@Override
	public Amitie findAmitie(int idPerson,int idAmi) {
		em.clear();
		var jpql = "SELECT a FROM Amitie a WHERE ((a.idpersonne = :idPerson AND a.idami = :idAmi) OR (a.idpersonne = :idAmi AND a.idami = :idPerson))  AND a.isValid = true";
		var query = em.createQuery( jpql,Amitie.class );
		query.setParameter( "idPerson", idPerson );
		query.setParameter( "idAmi", idAmi );
		return query.getSingleResult();
	}
	@Override
	public void AcceptFriendShip(Amitie amitie) {
		em.merge( amitie );
	}
	@Override
	public void DeleteFriendShip(Amitie amitie) {
		em.remove(amitie);
	}
	@Override
	public List<Personne> listerDemandes(int id) {
		em.clear();
		var jpql = "SELECT p FROM Personne p WHERE p.id IN (SELECT a.idami FROM Amitie a  WHERE a.idpersonne = :idPerson AND a.isValid = false) ORDER BY p.nom, p.prenom ";
		var query = em.createQuery( jpql, Personne.class );
		query.setParameter( "idPerson", id );
		return query.getResultList();
	}
	@Override
	public Amitie findDemand(int idPersonne, int idAmi) {
		em.clear();
		var jpql = "SELECT a FROM Amitie a WHERE a.idpersonne = :idPerson AND a.idami = :idAmi AND a.isValid = false";
		var query = em.createQuery( jpql,Amitie.class );
		query.setParameter( "idPerson", idPersonne );
		query.setParameter( "idAmi", idAmi );
		return query.getSingleResult();
	}
	

}
