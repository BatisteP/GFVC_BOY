package dao;

import java.util.ArrayList;
import java.util.Vector;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Nom du serveur : mysql.iutrs.unistra.fr 
 * Gestion des bases : https://webetu.iutrs.unistra.fr/phpmyadmin/ 
 * Base : Airport 
 * Password : VCPej4PtQQcN
 */
public abstract class DAOAbstractFacade<T> {

	@PersistenceUnit(unitName = "GFVC_BOY")
	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("GFVC_BOY");
	@PersistenceContext(unitName = "GFVC_BOY")
	private EntityManager em;

	private Class<T> classeEntite;

	/**
	 * Constructeur
	 * 
	 * @param classeEntite La classe de l'objet metier
	 */
	public DAOAbstractFacade(Class<T> classeEntite) {
		this.classeEntite = classeEntite;
	}

	/**
	 * Methode abstraite a definir dans chaque sous-classe qui renvoie
	 * l'EntityManager correspondant a la classe.
	 * 
	 * @return l'entity manager
	 */
	protected EntityManager getEntityManager() {
		if (em == null)
			em = emfactory.createEntityManager();
		return em;
	}

	/**
	 * Methode de creation d'un objet (ajout dans la base).
	 * 
	 * @param entite
	 * @throws NamingException 
	 * @throws SystemException 
	 * @throws NotSupportedException 
	 * @throws HeuristicRollbackException 
	 * @throws HeuristicMixedException 
	 * @throws RollbackException 
	 * @throws IllegalStateException 
	 * @throws SecurityException 
	 */
	public T create(T entite) throws NamingException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		transaction.begin();
		//getEntityManager().getTransaction().begin();
		getEntityManager().persist(entite);
		getEntityManager().flush();
		//getEntityManager().getTransaction().commit();
		transaction.commit();
		return entite;
	}

	/**
	 * Methode de modification d'un objet.
	 * 
	 * @param entite
	 * @throws SystemException 
	 * @throws NotSupportedException 
	 * @throws NamingException 
	 * @throws HeuristicRollbackException 
	 * @throws HeuristicMixedException 
	 * @throws RollbackException 
	 * @throws IllegalStateException 
	 * @throws SecurityException 
	 */
	public void edit(T entite) throws NotSupportedException, SystemException, NamingException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		transaction.begin();
	    getEntityManager().joinTransaction();
	    getEntityManager().merge(entite);
	    transaction.commit();
	}

	/**
	 * Methode de suppression d'un objet.
	 * 
	 * @param entite
	 * @throws SystemException 
	 * @throws NotSupportedException 
	 * @throws HeuristicRollbackException 
	 * @throws HeuristicMixedException 
	 * @throws RollbackException 
	 * @throws IllegalStateException 
	 * @throws SecurityException 
	 * @throws NamingException 
	 */
	public void remove(T entite) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, NamingException {
		UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		  transaction.begin();
	        getEntityManager().joinTransaction();
	        getEntityManager().remove(getEntityManager().merge(entite));
	        transaction.commit();
	}

	/**
	 * Methode de recherche d'un objet a partir de son identifiant.
	 * 
	 * @param id
	 * @return
	 * @return
	 * @throws SystemException 
	 * @throws NotSupportedException 
	 * @throws NamingException 
	 * @throws HeuristicRollbackException 
	 * @throws HeuristicMixedException 
	 * @throws RollbackException 
	 * @throws IllegalStateException 
	 * @throws SecurityException 
	 */
	public T find(Object id) throws NotSupportedException, SystemException, NamingException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		
		T obj =  getEntityManager().find(classeEntite, id);
	
		return obj;
		
	}

	/**
	 * Methode recherchant tous les objets de ce type.
	 * 
	 * @return
	 */
	public ArrayList<T> findAll() {
		@SuppressWarnings("unchecked")
		CriteriaQuery<T> cq = (CriteriaQuery<T>) getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(classeEntite));
		Vector<T> v = (Vector<T>) getEntityManager().createQuery(cq).getResultList();
		if (v != null)
			return new ArrayList<T>(v);
		return null;
	}

	/**
	 * Methode renvoyant le nombre d'objet de ce type.
	 * 
	 * @return
	 */
	public int count() {
		CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(classeEntite);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}
}
