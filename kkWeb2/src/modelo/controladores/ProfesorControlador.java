package modelo.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Entidad;
import modelo.Materia;
import modelo.Profesor;

public class ProfesorControlador extends Controlador {

	//Instancia singleton
	private static ProfesorControlador instancia = null;
	
	/**
	 * Constructor
	 */
	public ProfesorControlador() {
		super("Profesor");
		// TODO Auto-generated constructor stub
	}

	/**
	 * Singleton
	 * @return
	 */
	public static ProfesorControlador getInstancia() {
		
		if (instancia == null) {
			instancia = new ProfesorControlador();
		}
		
		return instancia;
	}
	
	public Profesor findFirst() {
		
		return (Profesor) super.findFirst();
		
	}
	
	public Profesor findLast() {
		return (Profesor) super.findLast();
	}

	@Override
	public Profesor findNext(Entidad e) {
		// TODO Auto-generated method stub
		return (Profesor) super.findNext(e);
	}

	@Override
	public Profesor findPrevious(Entidad e) {
		// TODO Auto-generated method stub
		return (Profesor) super.findPrevious(e);
	}
	
	public Profesor findById(int id) {
		
		try {
			EntityManager em = Controlador.getEntityManagerFactory().createEntityManager();
			Query q = em.createNativeQuery("SELECT * FROM profesor where id=?", Profesor.class);
			q.setParameter(1, id);
			Profesor profesor = (Profesor) q.setMaxResults(1).getSingleResult();
			em.close();
			return profesor;
			
		} catch (NoResultException nrEx) {
			return null;
		}
		
		
	}
	
	public List<Profesor> findAllProfesor() {
		
		List<Profesor> profesores = new ArrayList<Profesor>();
		List<Entidad> entidades = super.findAll();
		
		for (Entidad entidad : entidades) {
			
			profesores.add((Profesor) entidad);
		}
		
		
		return profesores;
	}
}
