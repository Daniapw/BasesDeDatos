package modelo.controladores;

import java.util.ArrayList;
import java.util.List;

import modelo.Curso;
import modelo.Entidad;
import modelo.Materia;

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

	
	public List<Curso> findAllCursos() {
		
		List<Curso> cursos = new ArrayList<Curso>();
		List<Entidad> entidades = super.findAll();
		
		for (Entidad entidad : entidades) {
			
			
			cursos.add((Curso) entidad);
		}
		
		
		return cursos;
	}


	
	
}
