package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import gui.utils.CacheImagenes;
import modelo.Estudiante;
import modelo.TipologiaSexo;
import modelo.controladores.EstudianteControlador;

public class PanelGestionEstudiantes extends JPanel {

	public Estudiante actual = null;
	public PanelGenerico panel = new PanelGenerico();
	
	/**
	 * 
	 */
	public PanelGestionEstudiantes() {
		super();
		this.setLayout(new BorderLayout());
		
		this.add(toolbar(), BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
		
		actual = EstudianteControlador.getInstancia().findFirst();
		cargarDatosActual();
	}
	
	/**
	 * 
	 * @return
	 */
	public JToolBar toolbar() {
		
		JToolBar toolBar = new JToolBar();
		
		JButton jbtPrimero = new JButton();
		asignarFuncion(jbtPrimero, "gotostart.png", PanelGenerico.LOAD_FIRST);
		toolBar.add(jbtPrimero);

		JButton jbtPrevio = new JButton();
		asignarFuncion(jbtPrevio, "previous.png", PanelGenerico.LOAD_PREV);
		toolBar.add(jbtPrevio);

		JButton jbtSiguiente = new JButton();
		asignarFuncion(jbtSiguiente, "next.png", PanelGenerico.LOAD_NEXT);
		toolBar.add(jbtSiguiente);

		JButton jbtUltimo = new JButton();
		asignarFuncion(jbtUltimo, "gotoend.png", PanelGenerico.LOAD_LAST);
		toolBar.add(jbtUltimo);

		JButton jbtNuevo = new JButton();
		asignarFuncion(jbtNuevo, "nuevo.png", PanelGenerico.NEW);
		toolBar.add(jbtNuevo);

		JButton jbtGuardar = new JButton();
		asignarFuncion(jbtGuardar, "guardar.png", PanelGenerico.SAVE);
		toolBar.add(jbtGuardar);

		JButton jbtEliminar = new JButton();
		asignarFuncion(jbtEliminar, "eliminar.png", PanelGenerico.REMOVE);
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
				
				Estudiante obtenido = null;
				if (funcion == PanelGenerico.LOAD_FIRST) 
					obtenido = EstudianteControlador.getInstancia().findFirst();
				if (funcion == PanelGenerico.LOAD_PREV) 
					obtenido = EstudianteControlador.getInstancia().findPrevious(actual);
				if (funcion == PanelGenerico.LOAD_NEXT) 
					obtenido = EstudianteControlador.getInstancia().findNext(actual);
				if (funcion == PanelGenerico.LOAD_LAST) 
					obtenido = EstudianteControlador.getInstancia().findLast();
				if (funcion == PanelGenerico.NEW) 
					nuevo();
				if (funcion == PanelGenerico.SAVE) 
					guardar();
				if (funcion == PanelGenerico.REMOVE) 
					obtenido = eliminar();
				
				if (obtenido != null) {
					actual = obtenido;
					cargarDatosActual();
				}
			}});
	}
	
	/**
	 * 
	 */
	private void nuevo() {
		
		this.panel.getJtfID().setText("");
		this.panel.getJtfNombre().setText("");
		this.panel.getJtfPrimerApellido().setText("");
		this.panel.getJtfSegundoApellido().setText("");
		this.panel.getJtfDNI().setText("");
		this.panel.getJtfDireccion().setText("");
		this.panel.getJtfEmail().setText("");
		this.panel.getJtfTelefono().setText("");
		this.panel.cambiarImagen(null);
		this.panel.cambiarColor("");
		JOptionPane.showMessageDialog(null, "Por favor, introduzca los datos del nuevo registro");
	}
	
	/**
	 * 
	 */
	private void guardar () {
		Estudiante nuevoRegistro = new Estudiante();
		
		if (this.panel.jtfID.getText().trim().equals("")) {
			nuevoRegistro.setId(0);
		}
		else { 
			nuevoRegistro.setId(Integer.parseInt(this.panel.jtfID.getText()));
			nuevoRegistro.setImagen(panel.getImagen());
		}
		
		nuevoRegistro.setNombre(this.panel.getJtfNombre().getText());
		nuevoRegistro.setPrimerApellido(this.panel.getJtfPrimerApellido().getText());
		nuevoRegistro.setSegundoApellido(this.panel.getJtfSegundoApellido().getText());
		nuevoRegistro.setDni(this.panel.getJtfDNI().getText());
		nuevoRegistro.setDireccion(this.panel.getJtfDireccion().getText());
		nuevoRegistro.setEmail(this.panel.getJtfEmail().getText());
		nuevoRegistro.setTelefono(this.panel.getJtfTelefono().getText());
		nuevoRegistro.setTipologiaSexo((TipologiaSexo) panel.jcbSexo.getSelectedItem());
		nuevoRegistro.setColorPreferido(panel.getJtfColorPreferido());
		
		
		if (nuevoRegistro.getId() == 0) {
			EstudianteControlador.getInstancia().persist(nuevoRegistro);
		}
		else {
			EstudianteControlador.getInstancia().merge(nuevoRegistro);
		}
		
		this.panel.jtfID.setText("" + nuevoRegistro.getId());
		JOptionPane.showMessageDialog(this, "Guardado correctamente");
		
		this.actual = nuevoRegistro;
	}	

	
	/**
	 * 
	 * @return
	 */
	private Estudiante eliminar () {
		String respuestas[] = new String[] {"S�", "No"};
		int opcionElegida = JOptionPane.showOptionDialog(null, 
				"�Realmente desea eliminar el registro?", "Eliminaci�n del registro", 
		        JOptionPane.OK_CANCEL_OPTION, 
		        JOptionPane.OK_CANCEL_OPTION, 
		        CacheImagenes.getCacheImagenes().getIcono("confirm.png"), 
		        respuestas, respuestas[1]);

	    if(opcionElegida == 0) {
	    	Estudiante nuevoAMostrar = EstudianteControlador.getInstancia().findPrevious(actual);
	    	if (nuevoAMostrar == null) {
	    		nuevoAMostrar = EstudianteControlador.getInstancia().findNext(actual);
	    	}
	    	EstudianteControlador.getInstancia().remove(actual);
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
			
			panel.getJtfID().setText("" + actual.getId());
			panel.getJtfNombre().setText(actual.getNombre());
			panel.getJtfPrimerApellido().setText(actual.getPrimerApellido());			
			panel.getJtfSegundoApellido().setText(actual.getSegundoApellido());
			panel.getJtfDNI().setText(actual.getDni());
			panel.getJtfDireccion().setText(actual.getDireccion());
			panel.getJtfEmail().setText(actual.getEmail());
			panel.getJtfTelefono().setText(actual.getTelefono());
			panel.setImagen(actual.getImagen());

			//Elegir sexo en JCB
			for (int i = 0; i < panel.jcbSexo.getItemCount(); i++) {
				
				if (actual.getTipologiaSexo().getId() == panel.jcbSexo.getItemAt(i).getId()) {
					panel.jcbSexo.setSelectedIndex(i);
				}
				
			}
			
			//Cargar imagen de Longblob
			if (panel.getImagen() != null)
				panel.cambiarImagen(actual.getImagen());
			else
				panel.cambiarImagen(null);
			
			//Cambiar color favorito
			if (actual.getColorPreferido() != null)
				panel.cambiarColor(actual.getColorPreferido());
			else
				panel.cambiarColor("");
		}
	}
	
	
}
