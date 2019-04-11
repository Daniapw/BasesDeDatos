package modelo.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.Entidad;


public class Controlador {

	private static EntityManagerFactory entityManagerFactory;
	
	private String nombreEntidad = "";
	
	public Controlador(String nombreEntidad) {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("ProyectoJPA");
		}
		this.nombreEntidad = nombreEntidad;
	}

		
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Entidad find(int id) {
		try {
			EntityManager em = getEntityManagerFactory().createEntityManager();
			Entidad entidad = (Entidad) em.find(Class.forName("modelo." + this.nombreEntidad), id);
			em.close();
			return entidad;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Entidad findFirst () {
		try {
			EntityManager em = getEntityManagerFactory().createEntityManager();
			Query q = em.createQuery("SELECT o FROM " + nombreEntidad + " o order by o.id", Entidad.class);
			Entidad resultado = (Entidad) q.setMaxResults(1).getSingleResult();
			em.close();
			return resultado;
		}
		catch (NoResultException nrEx) {
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Entidad findLast () {
		try {
			EntityManager em = getEntityManagerFactory().createEntityManager();
			Query q = em.createQuery("SELECT o FROM " + nombreEntidad + " o order by o.id desc", Entidad.class);
			Entidad resultado = (Entidad) q.setMaxResults(1).getSingleResult();
			em.close();
			return resultado;
		}
		catch (NoResultException nrEx) {
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Entidad findNext (Entidad e) {
		try {
			EntityManager em = getEntityManagerFactory().createEntityManager();
			Query q = em.createQuery("SELECT e FROM " + this.nombreEntidad + " e where e.id > :idActual order by e.id", Entidad.class);
			q.setParameter("idActual", e.getId());
			Entidad resultado = (Entidad) q.setMaxResults(1).getSingleResult();
			em.close();
			return resultado;
		}
		catch (NoResultException nrEx) {
			return null;
		}
	}

	
	
	
	/**
	 * 
	 * @return
	 */
	public Entidad findPrevious (Entidad e) {
		try {
			EntityManager em = getEntityManagerFactory().createEntityManager();
			Query q = em.createQuery("SELECT e FROM " + this.nombreEntidad + " e where e.id < :idActual order by e.id desc", Entidad.class);
			q.setParameter("idActual", e.getId());
			Entidad resultado = (Entidad) q.setMaxResults(1).getSingleResult();
			em.close();
			return resultado;
		}
		catch (NoResultException nrEx) {
			return null;
		}
	}
	
	/**
	 * 
	 */
	public List<Entidad> findAll () {
		
		EntityManager em = getEntityManagerFactory().createEntityManager();
		
		Query q;
		List<Entidad> entidades = new ArrayList<Entidad>();
		try {
			q = em.createNativeQuery("SELECT * FROM " + this.nombreEntidad.toLowerCase() +";", Class.forName("modelo." + this.nombreEntidad));
			entidades= (List<Entidad>) q.getResultList();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		em.close();
		
		return entidades;
		
	}
	/**
	 * 
	 */
	public void deleteAll () {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		em.createQuery("DELETE FROM " + this.nombreEntidad + " o").executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	
	/**
	 * 
	 */
	public void persist (Entidad e) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * 
	 */
	public void merge (Entidad e) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		em.merge(e);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * 
	 */
	public void remove (Entidad e) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		Entidad actual = null;
		em.getTransaction().begin();
		if (!em.contains(e)) {
			actual = em.merge(e);
		}
		em.remove(actual);
		em.getTransaction().commit();
		em.close();
	}

	
}
