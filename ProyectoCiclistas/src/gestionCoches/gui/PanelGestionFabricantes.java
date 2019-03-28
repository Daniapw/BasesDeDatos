package gestionCoches.gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tutorialJava.capitulo8_AWT_SWING.utils.CacheImagenes;

@SuppressWarnings("serial")
public class PanelGestionFabricantes extends JPanel {
	
	GridBagConstraints gbc = new GridBagConstraints();
	JTextField jtID = new JTextField();
	JTextField jtCIF = new JTextField();
	JTextField jtNombre = new JTextField();
	JButton jbtPrimero = new JButton();
	JButton jbtAnterior= new JButton();
	JButton jbtSiguiente = new JButton();
	JButton jbtUltimo = new JButton();
	
	public PanelGestionFabricantes() {
		
		super();
		
		//Colocar componentes
		maquetarVentana();
		
		
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
				// TODO Auto-generated method stub
				
			}
			
		});

		//Boton anterior
		configuraBoton(jbtAnterior, "previous.png", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//Boton siguiente
		configuraBoton(jbtSiguiente, "next.png", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//Boton primero
		configuraBoton(jbtUltimo, "gotoend.png", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//Anadir botones
		panel.add(jbtPrimero);
		panel.add(jbtAnterior);
		panel.add(jbtSiguiente);
		panel.add(jbtUltimo);
		
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
