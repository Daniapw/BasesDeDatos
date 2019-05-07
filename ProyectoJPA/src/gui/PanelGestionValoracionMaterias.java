package gui;

import javax.swing.JPanel;

import modelo.Estudiante;
import modelo.Materia;
import modelo.Profesor;
import modelo.Valoracionmateria;
import modelo.controladores.EstudianteControlador;
import modelo.controladores.MateriaControlador;
import modelo.controladores.ProfesorControlador;
import modelo.controladores.ValoracionMateriaControlador;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class PanelGestionValoracionMaterias extends JPanel {
	
	//JCB de las materias
	private JComboBox<Materia> jcbMaterias = new JComboBox<Materia>();
	//JCB de los profesores
	private JComboBox<Profesor> jcbProfesores = new JComboBox<Profesor>();
	//Boton Refrescar
	private JButton jbtRefrescarAlumnado = new JButton("Refrescar alumnado");
	//Boton guardar
	private JButton jbtGuardar = new JButton("Guardar resultados del alumnado");
	//Panel alumnos
	private JPanel panelAlumnos = new JPanel();
	//Lista paneles notas
	private List<PanelNotaEstudiante> panelesNotas= new ArrayList<PanelNotaEstudiante>();
	
	//Registro actual
	private Valoracionmateria actual;
	
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
		gbc_lblNewLabel.insets = new Insets(5,5,5,5);
		panelJCBs.add(lblNewLabel, gbc_lblNewLabel);
		
		GridBagConstraints gbc_jcbMaterias = new GridBagConstraints();
		gbc_jcbMaterias.insets = new Insets(0, 0, 5, 5);
		gbc_jcbMaterias.fill = GridBagConstraints.BOTH;
		gbc_jcbMaterias.anchor = GridBagConstraints.WEST;
		gbc_jcbMaterias.gridx = 1;
		gbc_jcbMaterias.gridy = 0;
		gbc_jcbMaterias.insets = new Insets(5,5,5,5);
		panelJCBs.add(jcbMaterias, gbc_jcbMaterias);
		
		JLabel lblNewLabel_1 = new JLabel("Profesor:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		gbc_lblNewLabel_1.insets = new Insets(5,5,5,5);
		panelJCBs.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		GridBagConstraints gbc_jcbProfesores = new GridBagConstraints();
		gbc_jcbProfesores.insets = new Insets(0, 0, 0, 5);
		gbc_jcbProfesores.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbProfesores.gridx = 1;
		gbc_jcbProfesores.gridy = 1;
		gbc_jcbProfesores.insets = new Insets(5,5,5,5);
		panelJCBs.add(jcbProfesores, gbc_jcbProfesores);
		
		GridBagConstraints gbc_jbtRefrescarAlumnado = new GridBagConstraints();
		gbc_jbtRefrescarAlumnado.insets = new Insets(0, 15, 0, 0);
		gbc_jbtRefrescarAlumnado.gridx = 2;
		gbc_jbtRefrescarAlumnado.gridy = 1;
		gbc_jbtRefrescarAlumnado.insets = new Insets(5,5,5,5);
		panelJCBs.add(jbtRefrescarAlumnado, gbc_jbtRefrescarAlumnado);
		
		//Funcionalidad boton refrescar
		jbtRefrescarAlumnado.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				generarPanelesNotas();
				
			}
		});
		
		JPanel panelBtnGuardar = new JPanel();
		add(panelBtnGuardar, BorderLayout.SOUTH);
		
		//Configurar JBT Guardar
		jbtGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				guardar();
				
			}
		});
		
		panelBtnGuardar.add(jbtGuardar);
		
		add(panelAlumnos, BorderLayout.CENTER);
		GridBagLayout gbl_panelAlumnos = new GridBagLayout();
		gbl_panelAlumnos.columnWidths = new int[] {0};
		gbl_panelAlumnos.rowHeights = new int[]{0};
	
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
	 * Metodo guardar
	 */
	private void guardar() {

		Valoracionmateria nuevoRegistro = new Valoracionmateria();
		
		for (PanelNotaEstudiante panel : panelesNotas) {
			
			nuevoRegistro.setEstudiante(panel.getEstudiante());
			nuevoRegistro.setMateria(panel.getMateria());
			nuevoRegistro.setProfesor(panel.getProfesor());
			nuevoRegistro.setValoracion( panel.getNotaEstudiante());
			
			//Buscar si existe un registro con el controlador y guardar el resultado
			Valoracionmateria registroBuscado = ValoracionMateriaControlador.getInstancia().findByProfesorEstudianteMateria(nuevoRegistro);
			
			if (registroBuscado == null) {
				nuevoRegistro.setId(0);
				ValoracionMateriaControlador.getInstancia().persist(nuevoRegistro);
			}
			else {
				nuevoRegistro.setId(registroBuscado.getId());
				ValoracionMateriaControlador.getInstancia().merge(nuevoRegistro);
			}
			
			
		}
		
		JOptionPane.showMessageDialog(null, "Guardado correctamente");
	}
	
	/**
	 * Generar paneles de estudiantes y aniadirlos al panel central
	 */
	private void generarPanelesNotas() {
		
		GridBagConstraints gbc_panelAlumnos = new GridBagConstraints();
		
		gbc_panelAlumnos.insets = new Insets(5, 5, 5, 5);
		
		panelAlumnos.removeAll();
		panelesNotas.clear();
		List<Estudiante> estudiantes = EstudianteControlador.getInstancia().findAllEstudiante();
		Estudiante estActual;
		
		PanelNotaEstudiante panelNota;
		
		for (int i = 0; i < estudiantes.size(); i++) {
			
			estActual = estudiantes.get(i);
			
			gbc_panelAlumnos.gridx = 0;
			gbc_panelAlumnos.gridy = i;
			
			//Crear panel del estudiante actual
			panelNota = new PanelNotaEstudiante(estActual, (Profesor) jcbProfesores.getSelectedItem(), (Materia) jcbMaterias.getSelectedItem());
			
			//Anadir el panel que acabo de crear al panel central
			panelAlumnos.add(panelNota, gbc_panelAlumnos);

			//Anadirlo a la lista de PanelNotaEstudiante, el que muestra el nombre y la nota 
			panelesNotas.add(panelNota);
						
		}

		
		//Reflejar en GUI notas de los estudiantes si existen
		Valoracionmateria actual = new Valoracionmateria();
		
		for (PanelNotaEstudiante panel:panelesNotas) {
			
			actual=new Valoracionmateria(panel.getEstudiante(), panel.getProfesor(), panel.getMateria());
			
			Valoracionmateria registroEncontrado = ValoracionMateriaControlador.getInstancia().findByProfesorEstudianteMateria(actual);
			
			if (registroEncontrado != null) panel.setNotaEstudiante(registroEncontrado.getValoracion());
				
			
		}
		
		
		this.revalidate();
		this.repaint();
	}
}
