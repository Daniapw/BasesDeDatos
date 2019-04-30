package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Estudiante;

public class PanelNotaEstudiante extends JPanel {

	private JLabel nombreEstudiante = new JLabel();
	private JTextField notaEstudiante = new JTextField(20);
	private Estudiante estudiante;
	
	/**
	 * Constructor
	 * @param est
	 */
	public PanelNotaEstudiante(Estudiante est) {
		
		this.estudiante = est;
		this.nombreEstudiante.setText(est.getNombre() + " " + est.getPrimerApellido() + " " + est.getSegundoApellido());
		
		this.add(nombreEstudiante);
		this.add(notaEstudiante);
	}

	
	/**
	 * Getters y setters
	 * @return
	 */
	
	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public String getNotaEstudiante() {
		return notaEstudiante.getText();
	}

	public void setNotaEstudiante(String notaEstudiante) {
		this.notaEstudiante.setText(notaEstudiante);
	}
	
	
}
