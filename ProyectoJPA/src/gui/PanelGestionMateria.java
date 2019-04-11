package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import gui.utils.CacheImagenes;
import modelo.Curso;
import modelo.Materia;
import modelo.controladores.CursoControlador;
import modelo.controladores.MateriaControlador;

public class PanelGestionMateria extends JPanel {
	JTextField jtfID = new JTextField(10);
	JTextField jtfNombre = new JTextField(10);
	JTextField jtfAcronimo = new JTextField(10);
	JComboBox<Curso> jcbIDCurso = new JComboBox<Curso>();
	GridBagConstraints gbc = new GridBagConstraints();
	Materia actual = null;
	
	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;
	
	public PanelGestionMateria() {
		super();
		this.setLayout(new BorderLayout());
		
		this.add(toolbar(), BorderLayout.NORTH);
		this.add(getPanelCentral(), BorderLayout.CENTER);
		
		actual = MateriaControlador.getInstancia().findFirst();
		cargarDatosActual();
	}
	
	/**
	 * 
	 * @return
	 */
	public JToolBar toolbar() {
		
		JToolBar toolBar = new JToolBar();
		
		JButton jbtPrimero = new JButton();
		asignarFuncion(jbtPrimero, "gotostart.png", LOAD_FIRST);
		toolBar.add(jbtPrimero);

		JButton jbtPrevio = new JButton();
		asignarFuncion(jbtPrevio, "previous.png", LOAD_PREV);
		toolBar.add(jbtPrevio);

		JButton jbtSiguiente = new JButton();
		asignarFuncion(jbtSiguiente, "next.png", LOAD_NEXT);
		toolBar.add(jbtSiguiente);

		JButton jbtUltimo = new JButton();
		asignarFuncion(jbtUltimo, "gotoend.png", LOAD_LAST);
		toolBar.add(jbtUltimo);

		JButton jbtNuevo = new JButton();
		asignarFuncion(jbtNuevo, "nuevo.png", NEW);
		toolBar.add(jbtNuevo);

		JButton jbtGuardar = new JButton();
		asignarFuncion(jbtGuardar, "guardar.png", SAVE);
		toolBar.add(jbtGuardar);

		JButton jbtEliminar = new JButton();
		asignarFuncion(jbtEliminar, "eliminar.png", REMOVE);
		toolBar.add(jbtEliminar);

		return toolBar;
	}
	
	/**
	 * 
	 * @param jbt
	 * @param funcion
	 */
	private void asignarFuncion (JButton jbt, String icono, final int funcion) {
		jbt.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));
		jbt.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent arg0) {
				
				Materia obtenido = null;
				if (funcion == LOAD_FIRST) 
					obtenido = MateriaControlador.getInstancia().findFirst();
				if (funcion == LOAD_PREV) 
					obtenido = MateriaControlador.getInstancia().findPrevious(actual);
				if (funcion == LOAD_NEXT) 
					obtenido = MateriaControlador.getInstancia().findNext(actual);
				if (funcion == LOAD_LAST) 
					obtenido = MateriaControlador.getInstancia().findLast();
				if (funcion == NEW) 
					nuevo();
				if (funcion == SAVE) 
					guardar();
				if (funcion == REMOVE) 
					obtenido = eliminar();
				
				if (obtenido != null) {
					actual= obtenido;
					cargarDatosActual();
				}
			}});
	}
	
	/**
	 * 
	 * @return
	 */
	private JPanel getPanelCentral() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		double pesoCol1 = 0.15, pesoCol2 = 0.2;

		gbc.insets = new Insets(5, 5, 5, 5);
		
		//ID
		colocaComponente(0, 0, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		panel.add(new JLabel("ID:"), gbc);
		
		colocaComponente(1, 0, GridBagConstraints.WEST, pesoCol2, 0, GridBagConstraints.NONE);
		panel.add(jtfID, gbc);
		
		jtfID.setEnabled(false);
		
		//Nombre
		colocaComponente(0, 1, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		panel.add(new JLabel("Nombre:"), gbc);
		
		colocaComponente(1, 1, GridBagConstraints.WEST, pesoCol2, 0, GridBagConstraints.NONE);
		panel.add(jtfNombre, gbc);
		
		//Acronimo
		colocaComponente(0, 2, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		panel.add(new JLabel("Acrónimo:"), gbc);
		
		colocaComponente(1, 2, GridBagConstraints.WEST, pesoCol2, 0, GridBagConstraints.NONE);
		panel.add(jtfAcronimo, gbc);
		
		//ComboBox Curso
		colocaComponente(0, 3, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		panel.add(new JLabel("Curso:"), gbc);
		
		colocaComponente(1, 3, GridBagConstraints.WEST, pesoCol2, 0, GridBagConstraints.NONE);
		panel.add(jcbIDCurso, gbc);
		
		cargarCB();
		
		return panel;
	}
	
	private void cargarCB() {
		
		List<Curso> cursos = MateriaControlador.getInstancia().findAllCursos();
		for (Curso curso : cursos) {
			jcbIDCurso.addItem(curso);
		}
		
	}
	
	/**
	 * 
	 */
	private void nuevo() {
		
		jtfID.setText("");
		jtfNombre.setText("");
		jtfAcronimo.setText("");
		jcbIDCurso.setSelectedItem(0);
		JOptionPane.showMessageDialog(null, "Por favor, introduzca los datos del nuevo registro");
	}

	/**
	 * 
	 */
	private void guardar () {
		Materia nuevoRegistro = new Materia();
		
		if (this.jtfID.getText().trim().equals("")) 
			nuevoRegistro.setId(0);
		else 
			nuevoRegistro.setId(Integer.parseInt(this.jtfID.getText()));
		
		nuevoRegistro.setNombre(jtfNombre.getText());
		nuevoRegistro.setAcronimo(jtfAcronimo.getText());
		nuevoRegistro.setCurso((Curso) jcbIDCurso.getSelectedItem());
		
		if (nuevoRegistro.getId() == 0) {
			CursoControlador.getInstancia().persist(nuevoRegistro);
		}
		else {
			CursoControlador.getInstancia().merge(nuevoRegistro);
		}
		
		this.jtfID.setText("" + nuevoRegistro.getId());
		JOptionPane.showMessageDialog(this, "Guardado correctamente");
		
		this.actual = nuevoRegistro;
	}
	
	/**
	 * 
	 * @return
	 */
	private Materia eliminar () {
		String respuestas[] = new String[] {"S�", "No"};
		int opcionElegida = JOptionPane.showOptionDialog(null, 
				"�Realmente desea eliminar el registro?", "Eliminaci�n del registro", 
		        JOptionPane.OK_CANCEL_OPTION, 
		        JOptionPane.OK_CANCEL_OPTION, 
		        CacheImagenes.getCacheImagenes().getIcono("confirm.png"), 
		        respuestas, respuestas[1]);

	    if(opcionElegida == 0) {
	    	Materia nuevoAMostrar = MateriaControlador.getInstancia().findPrevious(actual);
	    	if (nuevoAMostrar == null) {
	    		nuevoAMostrar = MateriaControlador.getInstancia().findNext(actual);
	    	}
	    	CursoControlador.getInstancia().remove(actual);
			JOptionPane.showMessageDialog(this, "Eliminaci�n correcta");

	    	if (nuevoAMostrar != null) {
	    		actual = nuevoAMostrar;
	    	}
	    	else {
	    		nuevo();
	    	} 
	    }
	    return actual;
	}
	
	/**
	 * 
	 */
	private void cargarDatosActual () {
		if (this.actual != null) {
			this.jtfID.setText("" + this.actual.getId());
			this.jtfNombre.setText(this.actual.getNombre());
			this.jtfAcronimo.setText(this.actual.getAcronimo());
			this.jcbIDCurso.setSelectedItem(this.actual.getCurso());
		}
	}
	
	/**
	 * 
	 * @param gridX
	 * @param gridY
	 * @param pesoColumna
	 * @param pesoFila
	 * @param fill
	 */
	private void colocaComponente (int gridX, int gridY, int anchor, double pesoColumna, double pesoFila, int fill) {
		gbc.gridx = gridX;
		gbc.gridy = gridY;
		gbc.anchor = anchor;
		gbc.weightx = pesoColumna;
		gbc.weighty = pesoFila;
		gbc.fill = fill;
		
	}
}
