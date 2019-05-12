package gui;

import java.util.List;

import modelo.Estudiante;
import modelo.controladores.EstudianteControlador;

public class DatosEstudiantes {

	public static String[] getTitulosColumnas() {
		return new String[] {"Id", "Nombre", "1º Apellido", "2º Apellido", "DNI.", "Direccion", "Email", "Imagen", "Color preferido"};
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static Object[][] getDatosDeTabla() {
		// Obtengo todas las personas
		List<Estudiante> personas = EstudianteControlador.getInstancia().findAllEstudiante();
		// Preparo una estructura para pasar al constructor de la JTable
		Object[][] datos = new Object[personas.size()][9];
		// Cargo los datos de la lista de personas en la matriz de los datos
		for (int i = 0; i < personas.size(); i++) {
			Estudiante persona = personas.get(i);
			datos[i][0] = persona.getId();
			datos[i][1] = persona.getNombre();
			datos[i][2] = persona.getPrimerApellido();
			datos[i][3] = persona.getSegundoApellido();
			datos[i][4] = persona.getDni();
			datos[i][5] = persona.getDireccion();
			datos[i][6] = persona.getEmail();
			datos[i][7] = persona.getImagen();
			datos[i][8] = persona.getColorPreferido();
		}
		
		return datos;
	}
}
