package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Estudiante;
import modelo.Materia;
import modelo.Profesor;

public class PanelNotaEstudiante extends JPanel {

	private JLabel nombreEstudiante = new JLabel();
	private JTextField notaEstudiante = new JTextField();
	private Estudiante estudiante;
	private Profesor profesor;
	private Materia materia;
	
	/**
	 * Constructor
	 * @param est
	 */
	public PanelNotaEstudiante(Estudiante est, Profesor prof, Materia mat) {
		this.estudiante = est;
		this.profesor = prof;
		this.materia = mat;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		
		//Colocar JLabel nombre
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 0, 5);
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		
		this.nombreEstudiante.setText(est.getNombre() + " " + est.getPrimerApellido() + " " + est.getSegundoApellido());
		this.add(nombreEstudiante, gbc_lblNombre);

		//Colocar JTextField nota
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		notaEstudiante.setPreferredSize(new Dimension(25, 20));

		this.add(notaEstudiante, gbc_textField);
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
	
	public float getNotaEstudiante() {
		System.out.println("Panel para " + this.estudiante.getNombre() + " nota: " + notaEstudiante.getText() + " hc: " + this.hashCode());
		try {
			return Float.parseFloat(notaEstudiante.getText());
		}
		catch (Exception e) {
			e.printStackTrace();;
		}
		return 0;
	}

	public void setNotaEstudiante(String notaEstudiante) {
		this.notaEstudiante.setText(notaEstudiante);
	}


	public JLabel getNombreEstudiante() {
		return nombreEstudiante;
	}


	public void setNombreEstudiante(JLabel nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
	}


	public void setNotaEstudiante(JTextField notaEstudiante) {
		this.notaEstudiante = notaEstudiante;
	}


	public Profesor getProfesor() {
		return profesor;
	}


	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}


	public Materia getMateria() {
		return materia;
	}


	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	
	
}
