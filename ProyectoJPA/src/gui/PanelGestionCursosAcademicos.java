package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import gui.utils.CacheImagenes;
import modelo.Curso;
import modelo.controladores.CursoControlador;

public class PanelGestionCursosAcademicos extends JPanel {

	JTextField jtfID = new JTextField(10);
	JTextField jtfDesc = new JTextField(10);
	GridBagConstraints gbc = new GridBagConstraints();
	Curso cursoActual = null;
	
	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;
	
	public PanelGestionCursosAcademicos() {
		super();
		this.setLayout(new BorderLayout());
		
		this.add(toolbar(), BorderLayout.NORTH);
		this.add(getPanelCentral(), BorderLayout.CENTER);
		
		cursoActual = CursoControlador.getInstancia().findFirst();
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
				
				Curso obtenido = null;
				if (funcion == LOAD_FIRST) 
					obtenido = CursoControlador.getInstancia().findFirst();
				if (funcion == LOAD_PREV) 
					obtenido = CursoControlador.getInstancia().findPrevious(cursoActual);
				if (funcion == LOAD_NEXT) 
					obtenido = CursoControlador.getInstancia().findNext(cursoActual);
				if (funcion == LOAD_LAST) 
					obtenido = CursoControlador.getInstancia().findLast();
				if (funcion == NEW) 
					nuevo();
				if (funcion == SAVE) 
					guardar();
				if (funcion == REMOVE) 
					obtenido = eliminar();
				
				if (obtenido != null) {
					cursoActual = obtenido;
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
		
		//Desc
		colocaComponente(0, 1, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		panel.add(new JLabel("Descripción:"), gbc);
		
		colocaComponente(1, 1, GridBagConstraints.WEST, pesoCol2, 0, GridBagConstraints.NONE);
		panel.add(jtfDesc, gbc);
		
		
		return panel;
	}
	
	/**
	 * 
	 */
	private void nuevo() {
		
		jtfID.setText("");
		jtfDesc.setText("");
		JOptionPane.showMessageDialog(null, "Por favor, introduzca los datos del nuevo registro");
	}
	
	/**
	 * 
	 */
	/**
	 * 
	 */
	private void guardar () {
		Curso nuevoRegistro = new Curso();
		
		if (this.jtfID.getText().trim().equals("")) 
			nuevoRegistro.setId(0);
		else 
			nuevoRegistro.setId(Integer.parseInt(this.jtfID.getText()));
		
		nuevoRegistro.setDescripcion(jtfDesc.getText());
		
		if (nuevoRegistro.getId() == 0) {
			CursoControlador.getInstancia().persist(nuevoRegistro);
		}
		else {
			CursoControlador.getInstancia().merge(nuevoRegistro);
		}
		
		this.jtfID.setText("" + nuevoRegistro.getId());
		JOptionPane.showMessageDialog(this, "Guardado correctamente");
		
		this.cursoActual = nuevoRegistro;
	}
	
	/**
	 * 
	 * @return
	 */
	private Curso eliminar () {
		String respuestas[] = new String[] {"S�", "No"};
		int opcionElegida = JOptionPane.showOptionDialog(null, 
				"�Realmente desea eliminar el registro?", "Eliminaci�n del registro", 
		        JOptionPane.OK_CANCEL_OPTION, 
		        JOptionPane.OK_CANCEL_OPTION, 
		        CacheImagenes.getCacheImagenes().getIcono("confirm.png"), 
		        respuestas, respuestas[1]);

	    if(opcionElegida == 0) {
	    	Curso nuevoAMostrar = CursoControlador.getInstancia().findPrevious(cursoActual);
	    	if (nuevoAMostrar == null) {
	    		nuevoAMostrar = CursoControlador.getInstancia().findNext(cursoActual);
	    	}
	    	CursoControlador.getInstancia().remove(cursoActual);
			JOptionPane.showMessageDialog(this, "Eliminaci�n correcta");

	    	if (nuevoAMostrar != null) {
	    		cursoActual = nuevoAMostrar;
	    	}
	    	else {
	    		nuevo();
	    	} 
	    }
	    return cursoActual;
	}
	
	/**
	 * 
	 */
	private void cargarDatosActual () {
		if (this.cursoActual != null) {
			this.jtfID.setText("" + this.cursoActual.getId());
			this.jtfDesc.setText(this.cursoActual.getDescripcion());
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
