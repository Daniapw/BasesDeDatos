package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JSlider;
import modelo.Estudiante;
import modelo.Materia;
import modelo.Profesor;
import modelo.Valoracionmateria;
import modelo.controladores.EstudianteControlador;
import modelo.controladores.MateriaControlador;
import modelo.controladores.ProfesorControlador;
import modelo.controladores.ValoracionMateriaControlador;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PanelGestionValoracionMateria2 extends JPanel {

	//JCBs de materias y profesores
	private JComboBox<Materia> jcbMateria = new JComboBox<Materia>();
	private JComboBox<Profesor> jcbProfesor = new JComboBox<Profesor>();
	//JSlider para asignar nota
	private JSlider jSliderNota = new JSlider();
	//JLists de alumnos
	private JList<Estudiante> jListNoSeleccionados = new JList<Estudiante>();
	private JList<Estudiante> jListSeleccionados = new JList<Estudiante>();
	private DefaultListModel<Estudiante> defaultModelJListNoSelec = new DefaultListModel<Estudiante>();
	private DefaultListModel<Estudiante> defaultModelJListSelec = new DefaultListModel<Estudiante>();
	
	//JButtons para controlar JLists de alumnos
	private JButton jbtQuitarTodos = new JButton("<<");
	private JButton jbtQuitarUno = new JButton("<");
	private JButton jbtSeleccionarUno = new JButton(">");
	private JButton jbtSeleccionarTodos = new JButton(">>");
	//JButton para guardar
	private JButton jbtGuardarNotas = new JButton("Guardar notas del alumnado");
	private JButton jbtActualizarAlumnos = new JButton("Actualizar alumnado");


	/**
	 * Create the panel.
	 */
	public PanelGestionValoracionMateria2() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelJCBs = new JPanel();
		panelJCBs.setBorder(new LineBorder(Color.GRAY));
		add(panelJCBs, BorderLayout.NORTH);
		GridBagLayout gbl_panelJCBs = new GridBagLayout();
		gbl_panelJCBs.columnWidths = new int[] {0, 0, 2};
		gbl_panelJCBs.rowHeights = new int[] {0, 0, 0, 0, 2};
		gbl_panelJCBs.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelJCBs.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelJCBs.setLayout(gbl_panelJCBs);
		
		JLabel lblNewLabel = new JLabel("Materia:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelJCBs.add(lblNewLabel, gbc_lblNewLabel);
		
		GridBagConstraints gbc_jcbMateria = new GridBagConstraints();
		gbc_jcbMateria.insets = new Insets(5, 5, 5, 0);
		gbc_jcbMateria.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbMateria.gridx = 1;
		gbc_jcbMateria.gridy = 0;
		panelJCBs.add(jcbMateria, gbc_jcbMateria);
		
		JLabel lblNewLabel_1 = new JLabel("Profesor:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panelJCBs.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		GridBagConstraints gbc_jcbProfesor = new GridBagConstraints();
		gbc_jcbProfesor.insets = new Insets(5, 5, 5, 0);
		gbc_jcbProfesor.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbProfesor.gridx = 1;
		gbc_jcbProfesor.gridy = 1;
		panelJCBs.add(jcbProfesor, gbc_jcbProfesor);
		
		JLabel lblNewLabel_2 = new JLabel("Nota");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panelJCBs.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		//Configurar JSlider
		jSliderNota.setPaintLabels(true);
		jSliderNota.setMinimum(1);
		jSliderNota.setMaximum(10);
		jSliderNota.setPaintTicks(true);
		jSliderNota.setMajorTickSpacing(1);
		
		GridBagConstraints gbc_jSliderNota = new GridBagConstraints();
		gbc_jSliderNota.insets = new Insets(5, 5, 5, 10);
		gbc_jSliderNota.fill = GridBagConstraints.BOTH;
		gbc_jSliderNota.gridx = 1;
		gbc_jSliderNota.gridy = 2;
		panelJCBs.add(jSliderNota, gbc_jSliderNota);
		
		GridBagConstraints gbc_jbtActualizarAlumnos = new GridBagConstraints();
		gbc_jbtActualizarAlumnos.anchor = GridBagConstraints.EAST;
		gbc_jbtActualizarAlumnos.gridx = 1;
		gbc_jbtActualizarAlumnos.gridy = 3;
		panelJCBs.add(jbtActualizarAlumnos, gbc_jbtActualizarAlumnos);
		
		jbtActualizarAlumnos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		JPanel panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelCentral.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelCentral.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		
		JLabel lblAlumnos = new JLabel("Alumnos no seleccionados");
		GridBagConstraints gbc_lblAlumnos = new GridBagConstraints();
		gbc_lblAlumnos.insets = new Insets(10, 5, 5, 5);
		gbc_lblAlumnos.gridx = 0;
		gbc_lblAlumnos.gridy = 1;
		panelCentral.add(lblAlumnos, gbc_lblAlumnos);
		
		JLabel lblAlumnosSeleccionados = new JLabel("Alumnos seleccionados");
		GridBagConstraints gbc_lblAlumnosSeleccionados = new GridBagConstraints();
		gbc_lblAlumnosSeleccionados.insets = new Insets(10, 5, 5, 5);
		gbc_lblAlumnosSeleccionados.gridx = 2;
		gbc_lblAlumnosSeleccionados.gridy = 1;
		panelCentral.add(lblAlumnosSeleccionados, gbc_lblAlumnosSeleccionados);
		
		GridBagConstraints gbc_jListNoSeleccionados = new GridBagConstraints();
		gbc_jListNoSeleccionados.fill = GridBagConstraints.BOTH;
		gbc_jListNoSeleccionados.insets = new Insets(5, 5, 5, 0);
		gbc_jListNoSeleccionados.gridx = 0;
		gbc_jListNoSeleccionados.gridy = 2;
		panelCentral.add(jListNoSeleccionados, gbc_jListNoSeleccionados);
		
		JPanel panelBotonesSeleccion = new JPanel();
		GridBagConstraints gbc_panelBotonesSeleccion = new GridBagConstraints();
		gbc_panelBotonesSeleccion.insets = new Insets(5, 5, 5, 5);
		gbc_panelBotonesSeleccion.fill = GridBagConstraints.VERTICAL;
		gbc_panelBotonesSeleccion.gridx = 1;
		gbc_panelBotonesSeleccion.gridy = 2;
		panelCentral.add(panelBotonesSeleccion, gbc_panelBotonesSeleccion);
		GridBagLayout gbl_panelBotonesSeleccion = new GridBagLayout();
		gbl_panelBotonesSeleccion.columnWidths = new int[]{0, 0};
		gbl_panelBotonesSeleccion.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelBotonesSeleccion.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelBotonesSeleccion.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelBotonesSeleccion.setLayout(gbl_panelBotonesSeleccion);
		
		GridBagConstraints gbc_jbtQuitarTodos = new GridBagConstraints();
		gbc_jbtQuitarTodos.insets = new Insets(5, 0, 5, 0);
		gbc_jbtQuitarTodos.gridx = 0;
		gbc_jbtQuitarTodos.gridy = 0;
		panelBotonesSeleccion.add(jbtQuitarTodos, gbc_jbtQuitarTodos);
		
		GridBagConstraints gbc_jbtQuitarUno = new GridBagConstraints();
		gbc_jbtQuitarUno.insets = new Insets(5, 0, 5, 0);
		gbc_jbtQuitarUno.gridx = 0;
		gbc_jbtQuitarUno.gridy = 1;
		panelBotonesSeleccion.add(jbtQuitarUno, gbc_jbtQuitarUno);
		
		GridBagConstraints gbc_jbtSeleccionarUno = new GridBagConstraints();
		gbc_jbtSeleccionarUno.insets = new Insets(5, 0, 5, 0);
		gbc_jbtSeleccionarUno.gridx = 0;
		gbc_jbtSeleccionarUno.gridy = 2;
		panelBotonesSeleccion.add(jbtSeleccionarUno, gbc_jbtSeleccionarUno);
		
		GridBagConstraints gbc_jbtSeleccionarTodos = new GridBagConstraints();
		gbc_jbtSeleccionarTodos.insets = new Insets(5, 0, 5, 0);
		gbc_jbtSeleccionarTodos.gridx = 0;
		gbc_jbtSeleccionarTodos.gridy = 3;
		panelBotonesSeleccion.add(jbtSeleccionarTodos, gbc_jbtSeleccionarTodos);
		
		GridBagConstraints gbc_jListSeleccionados = new GridBagConstraints();
		gbc_jListSeleccionados.insets = new Insets(5, 0, 5, 5);
		gbc_jListSeleccionados.fill = GridBagConstraints.BOTH;
		gbc_jListSeleccionados.gridx = 2;
		gbc_jListSeleccionados.gridy = 2;
		panelCentral.add(jListSeleccionados, gbc_jListSeleccionados);
		
		JPanel panelBtnGuardar = new JPanel();
		add(panelBtnGuardar, BorderLayout.SOUTH);
		
		panelBtnGuardar.add(jbtGuardarNotas);
		
		
		//Cargar JCBs
		cargarJCBs();
		
		//Cargar JList
		cargarListaAlumnos();
		
		//Configurar botones
		configurarBotones();
	}

	/**
	 * Cargar JCBs
	 */
	private void cargarJCBs() {
		
		List<Profesor> profesores = ProfesorControlador.getInstancia().findAllProfesor();
		List<Materia> materias= MateriaControlador.getInstancia().findAllMateria();
		
		//JCB Profesores
		for (Profesor profesor:profesores) {
			jcbProfesor.addItem(profesor);
		}
		
		//JCB Materias
		for (Materia materia:materias) {
			jcbMateria.addItem(materia);
		}
	}
	
	/**
	 * Cargar Jlist
	 */
	private void cargarListaAlumnos() {
				
		List<Estudiante> estudiantes = EstudianteControlador.getInstancia().findAllEstudiante();
		
		for (Estudiante est:estudiantes) {
			
			defaultModelJListNoSelec.addElement(est);

		}
		
		jListNoSeleccionados.setModel(defaultModelJListNoSelec);
		jListSeleccionados.setModel(defaultModelJListSelec);
	}
	
	/**
	 * Metodo guardar
	 */
	private void guardar() {

		for (int i = 0; i < defaultModelJListSelec.getSize(); i++) {
			
			Valoracionmateria nuevoRegistro = new Valoracionmateria(defaultModelJListSelec.getElementAt(i), 
					(Profesor) jcbProfesor.getSelectedItem(), (Materia) jcbMateria.getSelectedItem());
			
			nuevoRegistro.setValoracion((float) jSliderNota.getValue());
			
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
	 * Metodo para configurar los botones
	 */
	private void configurarBotones() {
		
		//Guardar
		jbtGuardarNotas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				guardar();
				
			}
		});
		
		//Boton quitar todos
		jbtQuitarTodos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Estudiante est;
				
				for (int i = 0; i < defaultModelJListSelec.getSize(); i++) {
					
					est = defaultModelJListSelec.get(i);
					defaultModelJListNoSelec.addElement(est);
					
				}
				
				defaultModelJListSelec.removeAllElements();				
			}
		});
		
		//Boton quitar uno
		jbtQuitarUno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int elegido;
				
				if (jListSeleccionados.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione un alumno");
				}
				else {
					elegido = jListSeleccionados.getSelectedIndex();
					Estudiante est = jListSeleccionados.getSelectedValue();
					defaultModelJListNoSelec.addElement(est);
					defaultModelJListSelec.removeElementAt(elegido);		
				}
				
			}
		});
		
		//Boton seleccionar uno
		jbtSeleccionarUno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int elegido;
				
				if (jListNoSeleccionados.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione un alumno");
				}
				else {
					elegido = jListNoSeleccionados.getSelectedIndex();
					Estudiante est = jListNoSeleccionados.getSelectedValue();
					defaultModelJListSelec.addElement(est);
					defaultModelJListNoSelec.removeElementAt(elegido);		
				}
				
			}
		});
		
		
		//Boton seleccionar todos
		jbtSeleccionarTodos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Estudiante est;
				for (int i = 0; i < defaultModelJListNoSelec.getSize(); i++) {
					
					est = defaultModelJListNoSelec.get(i);
					defaultModelJListSelec.addElement(est);
					
				}
				
				defaultModelJListNoSelec.removeAllElements();
			}
		});
		
		
	}
	
}
