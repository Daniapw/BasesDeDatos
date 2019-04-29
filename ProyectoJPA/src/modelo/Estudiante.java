package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estudiante database table.
 * 
 */
@Entity
@Table(name="estudiante")
@NamedQuery(name="Estudiante.findAll", query="SELECT e FROM Estudiante e")
public class Estudiante extends Entidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="apellido1")
	private String primerApellido;

	@Column(name="apellido2")
	private String segundoApellido;

	private String colorPreferido;

	private String direccion;

	private String dni;

	private String email;

	@Lob
	private byte[] imagen;

	private String nombre;

	private String telefono;

	//bi-directional many-to-one association to TipologiaSexo
	@ManyToOne
	@JoinColumn(name="idTipologiaSexo")
	private TipologiaSexo tipologiaSexo;

	//bi-directional many-to-one association to Valoracionmateria
	@OneToMany(mappedBy="estudiante")
	private List<Valoracionmateria> valoracionMaterias;

	public Estudiante() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getColorPreferido() {
		return this.colorPreferido;
	}

	public void setColorPreferido(String colorPreferido) {
		this.colorPreferido = colorPreferido;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public TipologiaSexo getTipologiaSexo() {
		return this.tipologiaSexo;
	}

	public void setTipologiaSexo(TipologiaSexo tipologiaSexo) {
		this.tipologiaSexo = tipologiaSexo;
	}

	public List<Valoracionmateria> getValoracionMaterias() {
		return this.valoracionMaterias;
	}

	public void setValoracionMaterias(List<Valoracionmateria> valoracionMaterias) {
		this.valoracionMaterias = valoracionMaterias;
	}

	public Valoracionmateria addValoracionMateria(Valoracionmateria valoracionMateria) {
		getValoracionMaterias().add(valoracionMateria);
		valoracionMateria.setEstudiante(this);

		return valoracionMateria;
	}

	public Valoracionmateria removeValoracionMateria(Valoracionmateria valoracionMateria) {
		getValoracionMaterias().remove(valoracionMateria);
		valoracionMateria.setEstudiante(null);

		return valoracionMateria;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}