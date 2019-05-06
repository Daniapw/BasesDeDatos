package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import gui.utils.Apariencia;


public class VentanaPrincipal extends JFrame {
	
	static {
		Apariencia.setAparienciasOrdenadas(Apariencia.aparienciasOrdenadas);
	}

	/**
	 * 
	 */
	public VentanaPrincipal () {
		super ("Centro Educativo");
		this.setBounds(0, 0, 500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Men� de la aplicaci�n
		this.setJMenuBar(new Menu());
		
		// Establezco el panel principal de la aplicaci�n y la barra de herramientas
		this.setLayout(new BorderLayout());
		this.add(new ToolBar(), BorderLayout.NORTH);
	
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		VentanaPrincipal ventana = new VentanaPrincipal();
		ventana.setVisible(true);
	}

}
