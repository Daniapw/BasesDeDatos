package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;

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
	//JButton resetear
	private JButton jbtActualizarAlumnos = new JButton("Resetear alumnos seleccionados");
	//JScrollPanes de las JList
	private JScrollPane jScrollNoSelec = new JScrollPane();
	private JScrollPane jScrollSeleccionados = new JScrollPane();
	//JFormattedTextField de la fecha
	private JFormattedTextField jFormattedFecha = new JFormattedTextField();


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
		gbl_panelJCBs.rowHeights = new int[] {0, 0, 0, 0, 0, 2};
		gbl_panelJCBs.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelJCBs.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelJCBs.setLayout(gbl_panelJCBs);
		
		JLabel lblNewLabel = new JLabel("Materia:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelJCBs.add(lblNewLabel, gbc_lblNewLabel);
		
		GridBagConstraints gbc_jcbMateria = new GridBagConstraints();
		gbc_jcbMateria.insets = new Insets(5, 5, 5, 5);
		gbc_jcbMateria.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbMateria.gridx = 1;
		gbc_jcbMateria.gridy = 0;
		panelJCBs.add(jcbMateria, gbc_jcbMateria);
		
		JLabel lblNewLabel_1 = new JLabel("Profesor:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panelJCBs.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		GridBagConstraints gbc_jcbProfesor = new GridBagConstraints();
		gbc_jcbProfesor.insets = new Insets(5, 5, 5, 5);
		gbc_jcbProfesor.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbProfesor.gridx = 1;
		gbc_jcbProfesor.gridy = 1;
		panelJCBs.add(jcbProfesor, gbc_jcbProfesor);
		
		JLabel lblNewLabel_2 = new JLabel("Nota:");
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
		gbc_jSliderNota.fill = GridBagConstraints.HORIZONTAL;
		gbc_jSliderNota.gridx = 1;
		gbc_jSliderNota.gridy = 2;
		panelJCBs.add(jSliderNota, gbc_jSliderNota);
		
		JLabel lblFecha = new JLabel("Fecha:");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 0;
		gbc_lblFecha.gridy = 3;
		panelJCBs.add(lblFecha, gbc_lblFecha);
		
		GridBagConstraints gbc_jFormatedFecha = new GridBagConstraints();
		gbc_jFormatedFecha.anchor = GridBagConstraints.WEST;
		gbc_jFormatedFecha.insets = new Insets(5, 5, 5, 0);
		gbc_jFormatedFecha.gridx = 1;
		gbc_jFormatedFecha.gridy = 3;

		configurarFecha();
		panelJCBs.add(jFormattedFecha, gbc_jFormatedFecha);
		
		GridBagConstraints gbc_jbtActualizarAlumnos = new GridBagConstraints();
		gbc_jbtActualizarAlumnos.gridwidth = 2;
		gbc_jbtActualizarAlumnos.insets = new Insets(5, 5, 5, 5);
		gbc_jbtActualizarAlumnos.gridx = 1;
		gbc_jbtActualizarAlumnos.gridy = 4;
		panelJCBs.add(jbtActualizarAlumnos, gbc_jbtActualizarAlumnos);
		
		
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
		
		GridBagConstraints gbc_jScrollNoSelec = new GridBagConstraints();
		gbc_jScrollNoSelec.insets = new Insets(5, 5, 5, 5);
		gbc_jScrollNoSelec.fill = GridBagConstraints.BOTH;
		gbc_jScrollNoSelec.gridx = 0;
		gbc_jScrollNoSelec.gridy = 2;
		panelCentral.add(jScrollNoSelec, gbc_jScrollNoSelec);
		
		JPanel panelBotonesSeleccion = new JPanel();
		GridBagConstraints gbc_panelBotonesSeleccion = new GridBagConstraints();
		gbc_panelBotonesSeleccion.insets = new Insets(5, 5, 0, 5);
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
		
		GridBagConstraints gbc_jScrollSeleccionados = new GridBagConstraints();
		gbc_jScrollSeleccionados.insets = new Insets(5, 5, 5, 5);
		gbc_jScrollSeleccionados.fill = GridBagConstraints.BOTH;
		gbc_jScrollSeleccionados.gridx = 2;
		gbc_jScrollSeleccionados.gridy = 2;
		panelCentral.add(jScrollSeleccionados, gbc_jScrollSeleccionados);
		
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
		
		jScrollNoSelec.setViewportView(jListNoSeleccionados);
		
		jListNoSeleccionados.setModel(defaultModelJListNoSelec);
		jScrollSeleccionados.setViewportView(jListSeleccionados);
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
			
			if (jFormattedFecha.getValue() != null) {
				
				Date fechaIntroducida = (Date) jFormattedFecha.getValue();
				
				nuevoRegistro.setFecha(new java.sql.Date(fechaIntroducida.getTime()));
			}
			
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
	 * Metodo para configurar el JFormattedTextField para la fecha
	 */
	@SuppressWarnings("serial")
	private void configurarFecha() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		jFormattedFecha = new JFormattedTextField(new JFormattedTextField.AbstractFormatter() {
			
			
			@Override
			public Object stringToValue(String text) throws ParseException {
				try {
					return sdf.parse(text);
				} catch (Exception e) {
					return null;
				}
				
			}

			@Override
			public String valueToString(Object value) throws ParseException {
				if (value != null && value instanceof Date) {
					
					return sdf.format((Date) value); 
				}
				return "";
			}

		}); 
		
		
		jFormattedFecha.setPreferredSize(new Dimension(100,20));
		jFormattedFecha.setValue(new Date());
	}
	
	/**
	 * Metodo para configurar los botones
	 */
	private void configurarBotones() {
		
		//Guardar
		jbtGuardarNotas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (defaultModelJListSelec.isEmpty())
					JOptionPane.showMessageDialog(null, "No hay ningun alumno seleccionado");
				else
					guardar();
				
			}
		});
		
		//Boton quitar todos
		jbtQuitarTodos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				quitarTodos();				
			}
		});
		
		//Boton quitar uno
		jbtQuitarUno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				quitarUnoOMas();
				
			}
		});
		
		//Boton seleccionar uno o mas
		jbtSeleccionarUno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				seleccionarUnoOMas();
				
			}
		});
		
		
		//Boton seleccionar todos
		jbtSeleccionarTodos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				seleccionarTodos();
			}
		});
		
		//Boton actualizar alumnado
		jbtActualizarAlumnos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarAlumnado();
				
			}
		});
		
	}
	
	/**
	 * 
	 */
	private void quitarTodos() {
		
		Estudiante est;
		
		for (int i = 0; i < defaultModelJListSelec.getSize(); i++) {
			
			est = defaultModelJListSelec.get(i);
			defaultModelJListNoSelec.addElement(est);
			
		}
		
		defaultModelJListSelec.removeAllElements();
		
	}
	
	/**
	 * 
	 */
	private void quitarUnoOMas() {
		
		int elegidos[];
		
		if (jListSeleccionados.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, seleccione un alumno");
		}
		else {
			elegidos = jListSeleccionados.getSelectedIndices();
			
			
			for (int i = elegidos.length-1; i >= 0; i--) {
				Estudiante est = defaultModelJListSelec.getElementAt(elegidos[i]);
				defaultModelJListNoSelec.addElement(est);
				defaultModelJListSelec.removeElementAt(elegidos[i]);	
				
			}
			
		}
		
	}
	
	/**
	 * 
	 */
	private void seleccionarTodos() {
		
		Estudiante est;
		for (int i = 0; i < defaultModelJListNoSelec.getSize(); i++) {
			
			est = defaultModelJListNoSelec.get(i);
			defaultModelJListSelec.addElement(est);
			
		}
		
		defaultModelJListNoSelec.removeAllElements();
		
	}
	
	/**
	 * 
	 */
	private void seleccionarUnoOMas() {
		
		int elegidos[];
		
		if (jListNoSeleccionados.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, seleccione un alumno");
		}
		else {
			elegidos = jListNoSeleccionados.getSelectedIndices();
			
			
			for (int i = elegidos.length-1; i >= 0; i--) {
				Estudiante est = defaultModelJListNoSelec.getElementAt(elegidos[i]);
				defaultModelJListSelec.addElement(est);
				defaultModelJListNoSelec.removeElementAt(elegidos[i]);	
				
			}
			
		}
		
	}
	
	/**
	 * 
	 */
	private void actualizarAlumnado() {
		Estudiante est;
		
		for (int i = 0; i < defaultModelJListSelec.getSize(); i++) {
			
			est = defaultModelJListSelec.get(i);
			defaultModelJListNoSelec.addElement(est);
			
		}
		
		defaultModelJListSelec.removeAllElements();	
		
	}
}
