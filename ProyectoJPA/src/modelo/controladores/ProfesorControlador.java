package modelo.controladores;

import modelo.Entidad;
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
	
}
