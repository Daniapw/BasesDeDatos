package gestionCoches.modelo.entidades;

public class Coche {
	private int id;
	private int idFabricante;
	private String bastidor;
	private String modelo;
	private String color;
	
	
	/**
	 * 
	 */
	public Coche() {
		super();
	}


	/**
	 * @param id
	 * @param idFabricante
	 * @param bastidor
	 * @param nombre
	 * @param color
	 */
	public Coche(int id, int idFabricante, String bastidor, String modelo, String color) {
		super();
		this.id = id;
		this.idFabricante = idFabricante;
		this.bastidor = bastidor;
		this.modelo = modelo;
		this.color = color;
	}
	
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdFabricante() {
		return idFabricante;
	}
	public void setIdFabricante(int idFabricante) {
		this.idFabricante = idFabricante;
	}
	public String getBastidor() {
		return bastidor;
	}
	public void setBastidor(String bastidor) {
		this.bastidor = bastidor;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String nombre) {
		this.modelo = nombre;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
	
}
