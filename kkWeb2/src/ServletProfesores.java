import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Profesor;
import modelo.controladores.ProfesorControlador;

@WebServlet(description = "Servlet Profesores", urlPatterns = { "/listadoProfesores" })

public class ServletProfesores extends ServletSupertipo {
	
	public ServletProfesores() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"<head>\r\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\r\n" + 
				"<title>Documento sin t�tulo</title>\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body>\r\n" + 
				"<h1>Listado de profesores</h1>\r\n" + 
				"<table width=\"95%\" border=\"1\">\r\n" + 
				"  <tr>\r\n" + 
				"    <th scope=\"col\">Nombre</th>\r\n" + 
				"    <th scope=\"col\">Primer apellido</th>\r\n" + 
				"    <th scope=\"col\">Segundo apellido</th>\r\n" + 
				"    <th scope=\"col\">DNI</th>\r\n" + 
				"    <th scope=\"col\">Direcci�n</th>\r\n" + 
				"    <th scope=\"col\">Email</th>\r\n" + 
				"    <th scope=\"col\">Tel�fono</th>\r\n" + 
				"    <th scope=\"col\">Color preferido</th>\r\n" + 
				"  </tr>\r\n");
		
		List<Profesor> profesores = ProfesorControlador.getInstancia().findAllProfesor();
		
		for (Profesor prof:profesores) {
			response.getWriter().append("" +
					"  <tr>\r\n" + 
					"    <td>" + "<a href=\"fichaProfesores?idProfesor=" + prof.getId() + "\">" + prof.getNombre() + "</a></td>\r\n" + 
					"    <td>" + prof.getPrimerApellido() + "</td>\r\n" + 
					"    <td>" + prof.getSegundoApellido() + "</td>\r\n" + 
					"    <td>" + prof.getDni() + "</td>\r\n" + 
					"    <td>" + prof.getDireccion() + "</td>\r\n" + 
					"    <td>" + prof.getEmail() + "</td>\r\n" + 
					"    <td>" + prof.getTelefono() + "</td>\r\n" + 
					"    <td>" + prof.getColorPreferido() + "</td>\r\n" + 
					"  </tr>\r\n");
			
		}
		
		response.getWriter().append("" + 
		"</table>\r\n" + 
		"<p/><input type=\"submit\"  name=\"nuevo\" value=\"Nuevo\"  onclick=\"window.location='FichaProfesor?idProfesor=0'\"/>" +
		"</body>\r\n" + 
		"</html>\r\n" + 
		"");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
}
