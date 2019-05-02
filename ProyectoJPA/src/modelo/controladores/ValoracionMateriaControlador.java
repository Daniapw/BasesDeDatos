package modelo.controladores;

import java.util.ArrayList;
import java.util.List;

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

	public List<Valoracionmateria> findAllMateria() {
		
		List<Valoracionmateria> valoracionMateria = new ArrayList<Valoracionmateria>();
		List<Entidad> entidades = super.findAll();
		
		for (Entidad entidad : entidades) {
			
			valoracionMateria.add((Valoracionmateria) entidad);
		}
		
		
		return valoracionMateria;
	}
	
}
