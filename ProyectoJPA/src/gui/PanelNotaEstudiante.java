package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import modelo.Estudiante;
import modelo.Materia;
import modelo.Profesor;

public class PanelNotaEstudiante extends JPanel {

	private JLabel nombreEstudiante = new JLabel();
	private JSpinner notaEstudiante = new JSpinner();
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
		gbc_lblNombre.fill = GridBagConstraints.BOTH;
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		
		nombreEstudiante.setPreferredSize(new Dimension(180,20));
		nombreEstudiante.setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.nombreEstudiante.setText(est.getNombre() + " " + est.getPrimerApellido() + " " + est.getSegundoApellido() + ":");
		this.add(nombreEstudiante, gbc_lblNombre);

		//Colocar JTextField nota
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.gridx = 1;
		gbc_textField.gridy = 0;

		//Hay que introducir los limites del SpinnerNumberModel como objetos Float para que getValue() devuelva un float
		Float uno=new Float(1);
		Float diez=new Float(10);
		SpinnerModel modelo = new SpinnerNumberModel(uno, uno, diez, uno);
		notaEstudiante.setModel(modelo);
		
		notaEstudiante.setPreferredSize(new Dimension(90,20));
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
	
	public Float getNotaEstudiante() {
		return  ((Float) notaEstudiante.getValue());
	}

	public void setNotaEstudiante(Float notaEstudiante) {
		this.notaEstudiante.setValue(notaEstudiante);
	}


	public JLabel getNombreEstudiante() {
		return nombreEstudiante;
	}


	public void setNombreEstudiante(JLabel nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
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
