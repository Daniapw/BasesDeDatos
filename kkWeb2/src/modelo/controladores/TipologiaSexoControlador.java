package modelo.controladores;

import java.util.ArrayList;
import java.util.List;

import modelo.Entidad;
import modelo.TipologiaSexo;

public class TipologiaSexoControlador extends Controlador {

	
	//Instancia singleton
	private static TipologiaSexoControlador instancia = null;
	
	/**
	 * Constructor
	 */
	public TipologiaSexoControlador() {
		super("TipologiaSexo");
		// TODO Auto-generated constructor stub
	}

	/**
	 * Singleton
	 * @return
	 */
	public static TipologiaSexoControlador getInstancia() {
		
		if (instancia == null) {
			instancia = new TipologiaSexoControlador();
		}
		
		return instancia;
	}
	
	public TipologiaSexo findFirst() {
		
		return (TipologiaSexo) super.findFirst();
		
	}
	
	public TipologiaSexo findLast() {
		return (TipologiaSexo) super.findLast();
	}

	@Override
	public TipologiaSexo findNext(Entidad e) {
		// TODO Auto-generated method stub
		return (TipologiaSexo) super.findNext(e);
	}

	@Override
	public TipologiaSexo findPrevious(Entidad e) {
		// TODO Auto-generated method stub
		return (TipologiaSexo) super.findPrevious(e);
	}

	public List<TipologiaSexo> findAllTipologiaSexos() {
		
		List<TipologiaSexo> sexos = new ArrayList<TipologiaSexo>();
		List<Entidad> entidades = super.findAll();
		
		for (Entidad entidad : entidades) {
			
			sexos.add((TipologiaSexo) entidad);
		}
		
		
		return sexos;
	}
	
}
