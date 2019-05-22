import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Profesor;
import modelo.controladores.ProfesorControlador;
import modelo.controladores.TipologiaSexoControlador;
import modelo.TipologiaSexo;

@WebServlet(description = "Ficha profesor", urlPatterns = { "/fichaProfesores" })

public class FichaProfesor extends ServletSupertipo {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FichaProfesor() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Profesor profesor = new Profesor();
		
		try {
			int idProfesor = getIntParameter(request, "idProfesor");
			profesor = (Profesor) ProfesorControlador.getInstancia().find(idProfesor);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String mensajeAlUsuario = "";
		
		if (request.getParameter("eliminar") != null) {
			// Intento eliminar el registro, si el borrado es correcto redirijo la petici�n hacia el listado de profesores
			try {
				ProfesorControlador.getInstancia().remove(profesor);
				response.sendRedirect(request.getContextPath() + "/listadoProfesores"); // Redirecci�n del response hacia el listado
			}
			catch (Exception ex) {
				mensajeAlUsuario = "Imposible eliminar. Es posible que existan restricciones.";
			}
		}
		
		// Segunda acci�n posible: guardar
		if (request.getParameter("guardar") != null) {
			// Obtengo todos los datos del profesor y los almaceno en BBDD
			try {
				profesor.setNombre(getStringParameter(request, "nombre"));
				profesor.setPrimerApellido(getStringParameter(request, "primerApellido"));
				profesor.setSegundoApellido(getStringParameter(request, "segundoApellido"));
				profesor.setTipologiaSexo((modelo.TipologiaSexo) modelo.controladores.TipologiaSexoControlador.getInstancia().find(getIntParameter(request, "idTipologiaSexo")));
				profesor.setDni(getStringParameter(request, "dni"));
				profesor.setDireccion(getStringParameter(request, "direccion"));
				profesor.setEmail(getStringParameter(request, "email"));
				profesor.setTelefono(getStringParameter(request, "telefono"));
				profesor.setColorPreferido(getStringParameter(request, "colorPreferido"));
				
				ProfesorControlador.getInstancia().persist(profesor);
				mensajeAlUsuario = "Guardado correctamente";
			} catch (FormularioIncorrectoRecibidoException e) {
				throw new ServletException(e);
			}
		}
		
		
		// Ahora muestro la pantalla de respuesta al usuario
		response.getWriter().append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"<head>\r\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\r\n" + 
				"<title>Documento sin t�tulo</title>\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body " +((mensajeAlUsuario != null && mensajeAlUsuario != "")? "onLoad=\"alert('" + mensajeAlUsuario + "');\"" : "")  + " >\r\n" + 
				"<h1>Ficha de profesor</h1>\r\n" + 
				"<a href=\"listadoProfesores\">Ir al listado de profesores</a>" +
				"<form id=\"form1\" name=\"form1\" method=\"post\" action=\"fichaProfesores\">\r\n" + 
				" <img src=\"../utils/DownloadImagenProfesor?idProfesor=" + ((profesor != null)? profesor.getId() : "") + "\" width='100px' height='100px'/>" +
				" <input type=\"hidden\" name=\"idProfesor\" value=\"" + ((profesor != null)? profesor.getId() : "") + "\"\\>" +
				"  <p>\r\n" + 
				"    <label for=\"nombre\">Nombre:</label>\r\n" + 
				"    <input name=\"nombre\" type=\"text\" id=\"nombre\"  value=\"" + ((profesor != null)? profesor.getNombre() : "") + "\" />\r\n" + 
				"  </p>\r\n" + 
				"  <p>\r\n" + 
				"    <label for=\"primerApellido\">Primer apellido:</label>\r\n" + 
				"    <input name=\"primerApellido\" type=\"text\" id=\"primerApellido\" value=\"" + ((profesor != null)? profesor.getPrimerApellido() : "") + "\" />\r\n" + 
				"  </p>\r\n" + 
				"  <p>\r\n" + 
				"    <label for=\"segundoApellido\">Segundo apellido:</label>\r\n" + 
				"    <input name=\"segundoApellido\" type=\"text\" id=\"segundoApellido\" value='" + ((profesor != null)? profesor.getSegundoApellido() : "") + "'/>\r\n" + 
				"  </p>\r\n" + 
				"  <p>\r\n" + 
				"    <label for=\"idTipologiaSexo\">Sexo:</label>\r\n" + 
				"    <select name=\"idTipologiaSexo\" id=\"idTipologiaSexo\">\r\n");
		
		// Inserto los valores de la tipolog�a del sexo del profesor y, si el registro tiene un valor concreto, lo establezco
		List<TipologiaSexo> sexos = TipologiaSexoControlador.getInstancia().findAllTipologiaSexos();
		for (TipologiaSexo sexo : sexos) {
			response.getWriter().append("<option value=\"" + sexo.getId() + "\" " + ((profesor != null && sexo.getId() == profesor.getTipologiaSexo().getId())? "selected=\"selected\"" : "") + ">" + sexo.getDescripcion() + "</option>");
		}
			
		response.getWriter().append("    </select>\r\n" + 
				"  </p>\r\n" + 
				"  <p>\r\n" + 
				"    <label for=\"dni\">DNI:</label>\r\n" + 
				"    <input name=\"dni\" type=\"text\" id=\"dni\" value='" + ((profesor != null)? profesor.getDni() : "") + "' />\r\n" + 
				"  </p>\r\n" + 
				"  <p>\r\n" + 
				"    <label for=\"direccion\">Direcci�n:</label>\r\n" + 
				"    <input name=\"direccion\" type=\"text\" id=\"direccion\" value='" + ((profesor != null)? profesor.getDireccion() : "") + "' />\r\n" + 
				"  </p>\r\n" + 
				"  <p>\r\n" + 
				"    <label for=\"email\">Email:</label>\r\n" + 
				"    <input name=\"email\" type=\"text\" id=\"email\" value='" + ((profesor != null)? profesor.getEmail() : "") + "'/>\r\n" + 
				"  </p>\r\n" + 
				"  <p>\r\n" + 
				"    <label for=\"telefono\">Tel�fono:</label>\r\n" + 
				"    <input name=\"telefono\" type=\"text\" id=\"telefono\" value='" + ((profesor != null)? profesor.getTelefono() : "") + "'/>\r\n" + 
				"  </p>\r\n" + 
				"  <p>\r\n" + 
				"    <label for=\"colorPreferido\">Color preferido:</label>\r\n" + 
				"    <input name=\"colorPreferido\" type=\"text\" id=\"colorPreferido\" value='" + ((profesor != null)? profesor.getColorPreferido() : "") + "'/>\r\n" + 
				"  </p>\r\n" + 
				"  <p>\r\n" + 
				"    <input type=\"submit\" name=\"guardar\" value=\"Guardar\" />\r\n" + 
				"    <input type=\"submit\" name=\"eliminar\" value=\"Eliminar\" />\r\n" + 
				"  </p>\r\n" + 
				"</form>" + 
				"</body>\r\n" + 
				"</html>\r\n" + 
				"");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	
	}
    
    
	
}
