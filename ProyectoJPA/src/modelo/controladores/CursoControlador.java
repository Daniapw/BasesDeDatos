package modelo.controladores;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Curso;
import modelo.Entidad;

public class CursoControlador extends Controlador {

	//Instancia singleton
	private static CursoControlador instancia = null;
	
	/**
	 * Constructor
	 */
	public CursoControlador() {
		super("Curso");
		// TODO Auto-generated constructor stub
	}

	/**
	 * Singleton
	 * @return
	 */
	public static CursoControlador getInstancia() {
		
		if (instancia == null) {
			instancia = new CursoControlador();
		}
		
		return instancia;
	}
	
	public Curso findFirst() {
		
		return (Curso) super.findFirst();
		
	}
	
	public Curso findLast() {
		return (Curso) super.findLast();
	}

	@Override
	public Curso findNext(Entidad e) {
		// TODO Auto-generated method stub
		return (Curso) super.findNext(e);
	}

	@Override
	public Curso findPrevious(Entidad e) {
		// TODO Auto-generated method stub
		return (Curso) super.findPrevious(e);
	}
	
}
