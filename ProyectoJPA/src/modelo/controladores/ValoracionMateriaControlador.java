package modelo.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Entidad;
import modelo.Materia;
import modelo.Valoracionmateria;

public class ValoracionMateriaControlador extends Controlador {
	//Instancia singleton
	private static ValoracionMateriaControlador instancia = null;
	
	/**
	 * Constructor
	 */
	public ValoracionMateriaControlador() {
		super("Valoracionmateria");
		// TODO Auto-generated constructor stub
	}

	/**
	 * Singleton
	 * @return
	 */
	public static ValoracionMateriaControlador getInstancia() {
		
		if (instancia == null) {
			instancia = new ValoracionMateriaControlador();
		}
		
		return instancia;
	}
	
	public Valoracionmateria findFirst() {
		
		return (Valoracionmateria) super.findFirst();
		
	}
	
	public Valoracionmateria findLast() {
		return (Valoracionmateria) super.findLast();
	}

	@Override
	public Valoracionmateria findNext(Entidad e) {
		// TODO Auto-generated method stub
		return (Valoracionmateria) super.findNext(e);
	}

	@Override
	public Valoracionmateria findPrevious(Entidad e) {
		// TODO Auto-generated method stub
		return (Valoracionmateria) super.findPrevious(e);
	}

	/**
	 * Encontrar ValoracionMateria existente segun IDs
	 * @param v
	 * @return
	 */
	public Valoracionmateria findByProfesorEstudianteMateria(Valoracionmateria v) {
		
		try {
			EntityManager em = Controlador.getEntityManagerFactory().createEntityManager();
			Query q = em.createNativeQuery("SELECT * FROM valoracionmateria v where v.idProfesor=? and v.idEstudiante=? and v.idMateria=?", Valoracionmateria.class);
			q.setParameter(1, v.getProfesor().getId());
			q.setParameter(2, v.getEstudiante().getId());
			q.setParameter(3, v.getMateria().getId());
			
			Valoracionmateria resultado = (Valoracionmateria) q.setMaxResults(1).getSingleResult();
			em.close();
			return resultado;
			
		} catch (NoResultException nrEx) {
			return null;
		}
		
		
	}
	
	public List<Valoracionmateria> findAllMateria() {
		
		List<Valoracionmateria> valoracionMateria = new ArrayList<Valoracionmateria>();
		List<Entidad> entidades = super.findAll();
		
		for (Entidad entidad : entidades) {
			
			valoracionMateria.add((Valoracionmateria) entidad);
		}
		
		
		return valoracionMateria;
	}
	
}
