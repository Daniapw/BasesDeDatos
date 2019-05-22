import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Estudiante;
import modelo.Materia;
import modelo.Profesor;
import modelo.Valoracionmateria;
import modelo.controladores.EstudianteControlador;
import modelo.controladores.MateriaControlador;
import modelo.controladores.ProfesorControlador;
import modelo.controladores.ValoracionMateriaControlador;

@WebServlet(description = "Ficha profesor", urlPatterns = { "/gestionNotas1" })

public class ServletGestionNotasEstudiantes extends ServletSupertipo {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Materia> materias = MateriaControlador.getInstancia().findAllMateria();
		List<Profesor> profesores = ProfesorControlador.getInstancia().findAllProfesor();
		List<Estudiante> estudiantes=EstudianteControlador.getInstancia().findAllEstudiante();
		
		response.getWriter().append(getCabeceraHTML("Gestion Notas") + 
				"<form id=\"form1\" method=\"post\" action=\"gestionNotas1\">\n" + 
				"    \n" + 
				"    <label for=\"materias\">Materias:</label>\n" + 
				"    <select name=\"materias\" id=\"materiasSelect\">\n" + 
				"    \n");
		
		//Cargar lista materias
		for (Materia materia:materias) {
			response.getWriter().append("<option value=\"" + materia.getId() + "\"" + "> " + materia.getNombre() +"</option>" );
		}
		
		response.getWriter().append("</select> <br>\n\n" + 
				"<label for=\"profesores\">Profesores:</label>\n" +
				"<select name=\"profesores\" id=\"profesoresSelect\">");
		
		//Cargar lista profesores
		for (Profesor profesor:profesores) {
			response.getWriter().append("<option value=\"" + profesor.getId() + "\"" + "> " + profesor.getNombre() + " " + profesor.getPrimerApellido() + " " + profesor.getSegundoApellido() +"</option>" );
		}
		
		response.getWriter().append("</select><br>"
				+ "<input type=\"submit\" name=\"refrescar\" value=\"Refrescar\" action=\"gestionNotas1\"/><br>");
		
				
		if (request.getParameter("refrescar") != null) {
		
			Materia materiaSeleccionada = (Materia) MateriaControlador.getInstancia().find(Integer.parseInt(request.getParameter("materias")));
			Profesor profesorSeleccionado = (Profesor) ProfesorControlador.getInstancia().find(Integer.parseInt(request.getParameter("profesores")));
			
			for (Estudiante estudiante:estudiantes) {
				Valoracionmateria valoracion = new Valoracionmateria(estudiante, profesorSeleccionado, materiaSeleccionada);
				
				valoracion= ValoracionMateriaControlador.getInstancia().findByProfesorEstudianteMateria(valoracion);
				
				String nombreCompletoEst = estudiante.getNombre() + " " + estudiante.getPrimerApellido() + " " + estudiante.getSegundoApellido();
				response.getWriter().append("<label for=\"nota"+ estudiante.getId() + "\">"  + nombreCompletoEst + "</label>"
						+ "<input type=\"text\" name=\"nota\"" + estudiante.getId() + " value=" + "\"" + valoracion.getValoracion() +"\""+"/><br>");
			}
		}
		
		response.getWriter().append("<input type=\"submit\" name=\"guardar\" value=\"Guardar\"/>\n");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}

	
	
}
