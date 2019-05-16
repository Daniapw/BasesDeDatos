package gui;

import javax.swing.JPanel;

import modelo.Estudiante;
import modelo.Valoracionmateria;
import modelo.controladores.EstudianteControlador;
import modelo.controladores.MateriaControlador;
import modelo.controladores.ValoracionMateriaControlador;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JEditorPane;

public class PanelTablaHTML extends JPanel {
	private JEditorPane editorPane;
	private List<String> nombresMaterias = new ArrayList<String>();

	/**
	 * Create the panel.
	 */
	public PanelTablaHTML() {
		setLayout(new BorderLayout(0, 0));
		
		nombresMaterias = MateriaControlador.getInstancia().findAllNombres();
		
		editorPane = getHTMLJEditorPane();
		add(editorPane, BorderLayout.CENTER);

	}

	/**
	 * 
	 * @return
	 */
	private JEditorPane getHTMLJEditorPane() {
		JEditorPane editor = new JEditorPane();
		editor.setContentType("text/html");
		editor.setText(getTabla());
		return editor;
	}
	
	/**
	 * 
	 * @return
	 */
	private String getTabla() {
		
		
		String str = "<h1 style='text-align:center'><u>Resumen de valoracion de materias</u></h1>\n"
				+ "<table border=1>"
					+ "<tr> <td></td>"
					+ getNombres()	
					+ "</tr>"
					+ getFilas() 
				+ "</table>";
	
		return str;
	}
	
	/**
	 * 
	 * @return
	 */
	private String getFilas() {
		
		List<Estudiante> estudiantes = new ArrayList<Estudiante>();
		
		estudiantes = EstudianteControlador.getInstancia().findAllEstudiante();
		
		StringBuffer html = new StringBuffer();
		
		for (Estudiante estudiante:estudiantes) {
			
			html.append("<tr>" 
						+ "<td>" + estudiante.toString() + "</td>");
			 for (String materia:nombresMaterias) {
				 html.append("<td style='text-align:center'>" + ValoracionMateriaControlador.getInstancia().findNotaMediaMateria(estudiante, materia) + "</td>");
			 }
			 
			 html.append("</tr>");
		}
		
		return html.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	private String getNombres() {
		StringBuffer nombres = new StringBuffer();
		for (String nombre:nombresMaterias) {
			if (nombre.equals("Lenguajes de Marcas y Sistemas de Gestión de la Información"))
				nombres.append("<td> <b>LMSGI</b> </td>");
			else
				nombres.append("<td> <b>"+ nombre +"</b> </td>");
		}
		 
		return nombres.toString();
	}
}
