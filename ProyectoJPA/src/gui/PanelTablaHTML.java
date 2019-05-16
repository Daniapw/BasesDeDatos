package gui;

import javax.swing.JPanel;

import modelo.Estudiante;
import modelo.Valoracionmateria;
import modelo.controladores.EstudianteControlador;
import modelo.controladores.ValoracionMateriaControlador;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JEditorPane;

public class PanelTablaHTML extends JPanel {
	private JEditorPane editorPane;

	/**
	 * Create the panel.
	 */
	public PanelTablaHTML() {
		setLayout(new BorderLayout(0, 0));
		
		editorPane = getHTMLJEditorPane();
		add(editorPane, BorderLayout.CENTER);

	}

	
	private JEditorPane getHTMLJEditorPane() {
		JEditorPane editor = new JEditorPane();

		editor.setContentType("text/html");
		editor.setText(getTabla());
		return editor;
	}
	
	
	private String getTabla() {
		
		
		String str = "<h1>Resumen de valoracion de materias</h1>\n"
				+ "<table border='1'>"
					+ "<tr> <td width='50px'></td>"
						+ "<td> Nombre </td>"
						+ "<td>Programacion </td> "
						+ "<td> LMSGI </td>"
						+ "<td> Sistemas Informaticos</tr>"
						+ "<td> Entornos de Desarrollo </tr>"
						+ "<td> Base de Datos </td> "
						+ "<td> FOL </td>"
					+ "</tr>"
					+ getFilas() 
				+ "</table>";
	
		return str;
	}
	
	private String getFilas() {
		
		List<Estudiante> estudiantes = new ArrayList<Estudiante>();
		
		estudiantes = EstudianteControlador.getInstancia().findAllEstudiante();
		
		StringBuffer html = new StringBuffer();
		
		for (Estudiante estudiante:estudiantes) {
			
			 html.append("<tr> <td>" + estudiante.toString() + "</td>"
						+ "<td> Nombre </td>"
						+ "<td>Programacion </td> "
						+ "<td> LMSGI </td>"
						+ "<td> Sistemas Informaticos</tr>"
						+ "<td> Entornos de Desarrollo </tr>"
						+ "<td> Base de Datos </td> "
						+ "<td> FOL </td>"
					+ "</tr>");
		}
		
			
		
		return html.toString();
		
		
	}
}
