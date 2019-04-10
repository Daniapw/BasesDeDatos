package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import gui.utils.CacheImagenes;


public class Menu extends JMenuBar {

	/**
	 * 
	 */
	public Menu () {
		// Men� Archivo de la aplicaci�n
        JMenu menuArchivo = new JMenu("Entidades");
        
        JMenuItem miCursoAcademico = new JMenuItem("Cursos académicos");
        miCursoAcademico.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JDialog dialogo = new JDialog();
				dialogo.setResizable(false);
				dialogo.setTitle("Gestion de cursos academicos");
				dialogo.setBounds(100, 100, 640, 480);
				dialogo.setContentPane(new PanelGestionCursosAcademicos());
				dialogo.setModal(true);
				dialogo.setVisible(true);
				
			}
		});
        
        menuArchivo.add(miCursoAcademico);
        
        this.add(menuArchivo);
	}
	
}
