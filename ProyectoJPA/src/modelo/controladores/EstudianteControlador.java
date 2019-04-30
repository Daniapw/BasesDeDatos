package modelo.controladores;

import java.util.ArrayList;
import java.util.List;

import modelo.Entidad;
import modelo.Estudiante;
import modelo.Profesor;

public class EstudianteControlador extends Controlador {

	//Instancia singleton
	private static EstudianteControlador instancia = null;
	
	/**
	 * Constructor
	 */
	public EstudianteControlador() {
		super("Estudiante");
		// TODO Auto-generated constructor stub
	}

	/**
	 * Singleton
	 * @return
	 */
	public static EstudianteControlador getInstancia() {
		
		if (instancia == null) {
			instancia = new EstudianteControlador();
		}
		
		return instancia;
	}
	
	public Estudiante findFirst() {
		
		return (Estudiante) super.findFirst();
		
	}
	
	public Estudiante findLast() {
		return (Estudiante) super.findLast();
	}

	@Override
	public Estudiante findNext(Entidad e) {
		// TODO Auto-generated method stub
		return (Estudiante) super.findNext(e);
	}

	@Override
	public Estudiante findPrevious(Entidad e) {
		// TODO Auto-generated method stub
		return (Estudiante) super.findPrevious(e);
	}
	
	public List<Estudiante> findAllEstudiante() {
		
		List<Estudiante> estudiantes = new ArrayList<Estudiante>();
		List<Entidad> entidades = super.findAll();
		
		for (Entidad entidad : entidades) {
			
			estudiantes.add((Estudiante) entidad);
		}
		
		
		return estudiantes;
	}
	
}
