package modelo.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Curso;
import modelo.Entidad;
import modelo.Materia;
import modelo.TipologiaSexo;
import modelo.Valoracionmateria;

public class MateriaControlador extends Controlador{
	
	//Instancia singleton
	private static MateriaControlador instancia = null;
	
	/**
	 * Constructor
	 */
	public MateriaControlador() {
		super("Materia");
		// TODO Auto-generated constructor stub
	}

	/**
	 * Singleton
	 * @return
	 */
	public static MateriaControlador getInstancia() {
		
		if (instancia == null) {
			instancia = new MateriaControlador();
		}
		
		return instancia;
	}
	
	public Materia findFirst() {
		
		return (Materia) super.findFirst();
		
	}
	
	public Materia findLast() {
		return (Materia) super.findLast();
	}

	@Override
	public Materia findNext(Entidad e) {
		// TODO Auto-generated method stub
		return (Materia) super.findNext(e);
	}

	@Override
	public Materia findPrevious(Entidad e) {
		// TODO Auto-generated method stub
		return (Materia) super.findPrevious(e);
	}

	public List<String> findAllNombres(){
		
		List<Materia> resultados = new ArrayList<Materia>();
		List<String> nombres = new ArrayList<String>();
		
		try {
			EntityManager em = Controlador.getEntityManagerFactory().createEntityManager();
			Query q = em.createNativeQuery("SELECT * FROM materia", Materia.class);
			
			resultados=(List<Materia>) q.getResultList();
			
			for (Materia materia:resultados) {
				nombres.add(materia.getNombre());
			}
			
			em.close();
			return nombres;
			
		} catch (NoResultException nrEx) {
			return null;
		}
		
	}
	
	public List<Materia> findAllMateria() {
		
		List<Materia> materias = new ArrayList<Materia>();
		List<Entidad> entidades = super.findAll();
		
		for (Entidad entidad : entidades) {
			
			materias.add((Materia) entidad);
		}
		
		
		return materias;
	}
	
	
}
