import java.sql.ResultSet;

public class Coche {

	private int id;
	private int idfabricante;
	private String bastidor;
	private String modelo;
	private String color;
	
	
	public Coche() {
		super();
		
	}
	/**
	 * 
	 * @param id
	 * @param idfabricante
	 * @param bastidor
	 * @param nombre
	 * @param color
	 */
	public Coche(int id, int idfabricante, String bastidor, String modelo, String color) {
		super();
		this.id = id;
		this.idfabricante = idfabricante;
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

	public int getIdfabricante() {
		return idfabricante;
	}

	public void setIdfabricante(int idfabricante) {
		this.idfabricante = idfabricante;
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

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
	
	
}
