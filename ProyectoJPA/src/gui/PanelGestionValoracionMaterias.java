package gui;

import javax.swing.JPanel;

import modelo.Estudiante;
import modelo.Materia;
import modelo.Profesor;
import modelo.controladores.EstudianteControlador;
import modelo.controladores.MateriaControlador;
import modelo.controladores.ProfesorControlador;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.BoxLayout;

public class PanelGestionValoracionMaterias extends JPanel {
	
	//JCB de las materias
	private JComboBox<Materia> jcbMaterias = new JComboBox<Materia>();
	//JCB de los profesores
	private JComboBox<Profesor> jcbProfesores = new JComboBox<Profesor>();
	//Boton Refrescar
	private JButton jbtRefrescarAlumnado = new JButton("Refrescar alumnado");
	//Boton guardar
	private JButton jbtGuardar = new JButton("Guardar");
	//Panel alumnos
	private JPanel panelAlumnos = new JPanel();
	//Lista paneles notas
	private List<PanelNotaEstudiante> panelesNotas= new ArrayList<PanelNotaEstudiante>();
	
	/**
	 * Codigo generado por WindowBuilder.
	 */
	public PanelGestionValoracionMaterias() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelJCBs = new JPanel();
		add(panelJCBs, BorderLayout.NORTH);
		GridBagLayout gbl_panelJCBs = new GridBagLayout();
		gbl_panelJCBs.columnWidths = new int[] {0, 0, 0, 1};
		gbl_panelJCBs.rowHeights = new int[] {0, 0, 1};
		gbl_panelJCBs.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelJCBs.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelJCBs.setLayout(gbl_panelJCBs);
		
		JLabel lblNewLabel = new JLabel("Materia:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelJCBs.add(lblNewLabel, gbc_lblNewLabel);
		
		GridBagConstraints gbc_jcbMaterias = new GridBagConstraints();
		gbc_jcbMaterias.insets = new Insets(0, 0, 5, 5);
		gbc_jcbMaterias.fill = GridBagConstraints.BOTH;
		gbc_jcbMaterias.anchor = GridBagConstraints.WEST;
		gbc_jcbMaterias.gridx = 1;
		gbc_jcbMaterias.gridy = 0;
		panelJCBs.add(jcbMaterias, gbc_jcbMaterias);
		
		JLabel lblNewLabel_1 = new JLabel("Profesor:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panelJCBs.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		GridBagConstraints gbc_jcbProfesores = new GridBagConstraints();
		gbc_jcbProfesores.insets = new Insets(0, 0, 0, 5);
		gbc_jcbProfesores.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbProfesores.gridx = 1;
		gbc_jcbProfesores.gridy = 1;
		panelJCBs.add(jcbProfesores, gbc_jcbProfesores);
		
		GridBagConstraints gbc_jbtRefrescarAlumnado = new GridBagConstraints();
		gbc_jbtRefrescarAlumnado.insets = new Insets(0, 15, 0, 0);
		gbc_jbtRefrescarAlumnado.gridx = 2;
		gbc_jbtRefrescarAlumnado.gridy = 1;
		panelJCBs.add(jbtRefrescarAlumnado, gbc_jbtRefrescarAlumnado);
		
		JPanel panelBtnGuardar = new JPanel();
		add(panelBtnGuardar, BorderLayout.SOUTH);
		
		panelBtnGuardar.add(jbtGuardar);
		
		add(panelAlumnos, BorderLayout.CENTER);
		GridBagLayout gbl_panelAlumnos = new GridBagLayout();
		gbl_panelAlumnos.columnWidths = new int[] {0};
		gbl_panelAlumnos.rowHeights = new int[]{0};
		gbl_panelAlumnos.columnWeights = new double[]{0.4, 0.8};
		gbl_panelAlumnos.rowWeights = new double[]{Double.MIN_VALUE};
		
		panelAlumnos.setLayout(gbl_panelAlumnos);

		//Cargar JCBs
		cargarJCBs();
		
		//Generar paneles con las notas de los estudiantes
		generarPanelesNotas();
	}
	
	/**
	 * Cargar JCBs
	 */
	private void cargarJCBs() {
		
		List<Profesor> profesores = ProfesorControlador.getInstancia().findAllProfesor();
		List<Materia> materias= MateriaControlador.getInstancia().findAllMateria();
		
		//JCB Profesores
		for (Profesor profesor:profesores) {
			jcbProfesores.addItem(profesor);
		}
		
		//JCB Materias
		for (Materia materia:materias) {
			jcbMaterias.addItem(materia);
		}
	}
	
	/**
	 * Generar paneles de estudiantes y aniadirlos al panel central
	 */
	private void generarPanelesNotas() {
		
		GridBagConstraints gbc_panelAlumnos = new GridBagConstraints();
		
		List<Estudiante> estudiantes = EstudianteControlador.getInstancia().findAllEstudiante();
		Estudiante estActual;
		int contadorEstActual = 0;
		
		PanelNotaEstudiante panelNota;
		
		for (int i = 0; i < estudiantes.size(); i++) {
			
			for (int j = 0; j <= 1; j++ ) {
				
				estActual = estudiantes.get(contadorEstActual);
				
				gbc_panelAlumnos.gridx = j;
				gbc_panelAlumnos.gridy = i;
				
				//Crear panel del estudiante actual
				panelNota = new PanelNotaEstudiante(estActual);
				
				//Anadirlo a la lista de PanelNotaEstudiante, el que muestra el nombre y la nota 
				panelesNotas.add(panelNota);
				
				//Anadir el panel que acabo de crear al panel central
				panelAlumnos.add(panelNota.getComponent(j), gbc_panelAlumnos);
				
				
			}
			
			contadorEstActual++;
			
		}
		

	}
}
