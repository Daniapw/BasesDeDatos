package gestionCoches.gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gestionCoches.modelo.ControladorBBDDConcesionarios;
import gestionCoches.modelo.ControladorBBDDFabricante;
import gestionCoches.modelo.entidades.Concesionario;
import gestionCoches.modelo.entidades.Fabricante;
import tutorialJava.capitulo8_AWT_SWING.utils.CacheImagenes;

public class PanelGestionConcesionario extends JPanel {
	
	GridBagConstraints gbc = new GridBagConstraints();
	JTextField jtID = new JTextField();
	JTextField jtCIF = new JTextField();
	JTextField jtNombre = new JTextField();
	JTextField jtLocalidad = new JTextField();
	JButton jbtPrimero = new JButton();
	JButton jbtAnterior= new JButton();
	JButton jbtSiguiente = new JButton();
	JButton jbtUltimo = new JButton();
	JButton jbtNuevo = new JButton();
	JButton jbtGuardar = new JButton();
	JButton jbtBorrar = new JButton();
	
	Concesionario concActual = new Concesionario();
			
	/**
	 * 
	 */
	public PanelGestionConcesionario() {
		
		super();
		
		//Colocar componentes
		maquetarVentana();
		
		//Cargar primer fabricante al inicio
		navegaAPrimero();
		
		//Anadir control rueda raton
		controlRuedaRaton();
	}
	
	/**
	 * 
	 */
	private void controlRuedaRaton () {
		this.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.getUnitsToScroll() < 0) {
					if (ControladorBBDDConcesionarios.getSiguienteConcesionario(concActual) != null) {
						navegaASiguiente();
					}
				}
				else {
					if (ControladorBBDDConcesionarios.getAnteriorConcesionario(concActual) != null) {
						navegaAAnterior();
					}
				}
			}
		});
	}
	
	/**
	 * Metodo que coloca cada componente en su sitio
	 */
	private void maquetarVentana() {
		double pesoCol1 = 0.15, pesoCol2 = 1;
		
		this.setLayout(new GridBagLayout());

		gbc.insets = new Insets(5, 5, 5, 5);
		
		//ID
		colocaComponente(0, 0, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		this.add(new JLabel("ID:"), gbc);
		
		colocaComponente(1, 0, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
		this.add(jtID, gbc);
		
		//CIF
		colocaComponente(0, 1, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		this.add(new JLabel("CIF:"), gbc);
		
		colocaComponente(1, 1, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
		this.add(jtCIF, gbc);
		
		//Nombre
		colocaComponente(0, 2, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		this.add(new JLabel("Nombre:"), gbc);
		
		colocaComponente(1, 2, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
		this.add(jtNombre, gbc);
		
		//Localidad
		colocaComponente(0, 3, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		this.add(new JLabel("Localidad:"), gbc);
		
		colocaComponente(1, 3, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
		this.add(jtLocalidad, gbc);
		
		// Incorporamos fila botones
		colocaComponente(0, 5, GridBagConstraints.NORTH, 1, 1, GridBagConstraints.BOTH);
		gbc.gridwidth = 2;
		this.add(getBotones(), gbc);		
	}
	
	/**
	 * Metodo para anadir los botones
	 * @return
	 */
	private JPanel getBotones() {
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new FlowLayout());
		
		//Boton primero
		configuraBoton(jbtPrimero, "gotostart.png", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				navegaAPrimero();
				
			}
			
		});

		//Boton anterior
		configuraBoton(jbtAnterior, "previous.png", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				navegaAAnterior();
				
			}
			
		});
		
		//Boton siguiente
		configuraBoton(jbtSiguiente, "next.png", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				navegaASiguiente();
				
			}
			
		});
		
		//Boton ultimo
		configuraBoton(jbtUltimo, "gotoend.png", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				navegaAUltimo();
				
			}
			
		});
		
		//Boton nuevo
		configuraBoton(jbtNuevo, "nuevo.png", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				anadirNuevo();
				
			}
			
		});
		
		//Boton guardar
		configuraBoton(jbtGuardar, "guardar.png", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				guardar();
				
			}
			
		});
		
		//Boton borrar
		configuraBoton(jbtBorrar, "eliminar.png", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				eliminar();
				
			}
			
		});
		
		//Anadir botones
		panel.add(jbtPrimero);
		panel.add(jbtAnterior);
		panel.add(jbtSiguiente);
		panel.add(jbtUltimo);
		panel.add(jbtNuevo);
		panel.add(jbtGuardar);
		panel.add(jbtBorrar);
		
		return panel;
	}

	/**
	 * 
	 * @param jbt
	 * @param icono
	 */
	private void configuraBoton (JButton jbt, String icono, ActionListener actionListener) {
		jbt.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));
		jbt.addActionListener(actionListener);
	}
	
	/**
	 * 
	 */
	private void navegaAPrimero() {
		
		concActual = ControladorBBDDConcesionarios.getPrimerConcesionario();
		
		cargaConcesionarioEnComponentesVisuales();
		actualizaEstadoBotonera();
	}
	
	/**
	 * 
	 */
	private void navegaAUltimo() {
		
		concActual = ControladorBBDDConcesionarios.getUltimoConcesionario();
		
		cargaConcesionarioEnComponentesVisuales();
		actualizaEstadoBotonera();
	}
	
	/**
	 * 
	 */
	private void navegaASiguiente() {
		
		concActual = ControladorBBDDConcesionarios.getSiguienteConcesionario(concActual);
		
		cargaConcesionarioEnComponentesVisuales();
		actualizaEstadoBotonera();
	}
	
	/**
	 * 
	 */
	private void navegaAAnterior() {
		
		concActual = ControladorBBDDConcesionarios.getAnteriorConcesionario(concActual);
		
		cargaConcesionarioEnComponentesVisuales();
		
		actualizaEstadoBotonera();
	}
	
	/**
	 * 
	 */
	private void anadirNuevo() {
		this.concActual = new Concesionario();
		this.concActual.setId(-1);
		this.jtID.setText("" + -1);
		this.jtCIF.setText("");
		this.jtNombre.setText("");
		this.jtLocalidad.setText("");

		// Actualizo la botonera
		this.actualizaEstadoBotonera();
	}
	
	/**
	 * 
	 */
	private void guardar() {
		// Es un alta nueva o una modificaci�n
		cargaConcesionarioDesdeComponentesVisuales();
		if (this.concActual.getId() == -1) { // Alta
			ControladorBBDDConcesionarios.guardarNuevoConcesionario(concActual);
			this.navegaAUltimo();
		}
		else { // Modificaci�n
			ControladorBBDDConcesionarios.modificarConcesionario(concActual);
		}

		// Actualizo la botonera
		this.actualizaEstadoBotonera();
	}
	
	/**
	 * 
	 */
	private void eliminar() {
		// Por regla general, siempre que eliminemos un coche navegaremos al siguiente
		// registro
		Concesionario concAEliminar = this.concActual;
		
		// Compruebo si el coche actual es el �ltimo coche
		if (ControladorBBDDConcesionarios.getUltimoConcesionario().getId() == this.concActual.getId()) {
			navegaAAnterior();
		}
		else {
			navegaASiguiente();
		}
		
		// Finalmente elimino el coche
		ControladorBBDDConcesionarios.eliminarConcesionario(concAEliminar);
		
		// Actualizo la botonera
		this.actualizaEstadoBotonera();
	}
	
	/**
	 * 
	 */
	private void actualizaEstadoBotonera() {
		if (ControladorBBDDConcesionarios.getAnteriorConcesionario(concActual) == null) {
			jbtPrimero.setEnabled(false);
			jbtAnterior.setEnabled(false);
		}
		else {
			jbtPrimero.setEnabled(true);
			jbtAnterior.setEnabled(true);
		}
		if (ControladorBBDDConcesionarios.getSiguienteConcesionario(concActual) == null) {
			jbtSiguiente.setEnabled(false);
			jbtUltimo.setEnabled(false);
		}
		else {
			jbtSiguiente.setEnabled(true);
			jbtUltimo.setEnabled(true);
		}
	}
	
	/**
	 * 
	 */
	private void cargaConcesionarioEnComponentesVisuales () {
		this.jtID.setText("" + concActual.getId());
		this.jtCIF.setText(concActual.getCif());
		this.jtNombre.setText(concActual.getNombre());
		this.jtLocalidad.setText(concActual.getLocalidad());
	
	}
	
	/**
	 * 
	 */
	private void cargaConcesionarioDesdeComponentesVisuales () {
		this.concActual.setId(Integer.parseInt(this.jtID.getText()));
		this.concActual.setCif(this.jtCIF.getText());
		this.concActual.setNombre(this.jtNombre.getText());
		this.concActual.setLocalidad(this.jtLocalidad.getText());
	}

	/**
	 * Metodo que configura las GridBagConstraints
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
