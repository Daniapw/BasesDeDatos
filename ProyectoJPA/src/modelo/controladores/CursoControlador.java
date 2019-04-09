package modelo.controladores;

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
	
}
