package modelo.controladores;

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
