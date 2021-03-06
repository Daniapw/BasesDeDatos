package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


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
				dialogo.setBounds(100, 100, 300, 200);
				dialogo.setContentPane(new PanelGestionCursosAcademicos());
				dialogo.setModal(true);
				dialogo.setVisible(true);
				
			}
		});
        
        JMenuItem miMaterias = new JMenuItem("Materias");
        miMaterias.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JDialog dialogo = new JDialog();
				dialogo.setResizable(false);
				dialogo.setTitle("Gestion de materias");
				dialogo.setBounds(100, 100, 300, 200);
				dialogo.setContentPane(new PanelGestionMateria());
				dialogo.setModal(true);
				dialogo.setVisible(true);
				
			}
		});
        
        JMenuItem miEstudiantes = new JMenuItem("Estudiantes");
        miEstudiantes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JDialog dialogo = new JDialog();
				dialogo.setResizable(false);
				dialogo.setTitle("Gestion de estudiantes");
				dialogo.setBounds(100, 100, 600, 450);
				dialogo.setContentPane(new PanelGestionEstudiantes());
				dialogo.setModal(true);
				dialogo.setVisible(true);
				
			}
		});
        
        JMenuItem miProfesores = new JMenuItem("Profesores");
        miProfesores.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JDialog dialogo = new JDialog();
				dialogo.setResizable(false);
				dialogo.setTitle("Gestion de profesores");
				dialogo.setBounds(100, 100, 600, 450);
				dialogo.setContentPane(new PanelGestionProfesores());
				dialogo.setModal(true);
				dialogo.setVisible(true);
				
			}
		});
        
        JMenuItem miValoracionMaterias = new JMenuItem("Valoracion materias");
        miValoracionMaterias.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JDialog dialogo = new JDialog();
				dialogo.setResizable(false);
				dialogo.setTitle("Gestion de notas");
				dialogo.setBounds(100, 100, 450, 300);
				dialogo.setContentPane(new PanelGestionValoracionMaterias());
				dialogo.setModal(true);
				dialogo.setVisible(true);
				
			}
		});
        
        JMenuItem miValoracionMaterias2 = new JMenuItem("Valoracion materias 2");
        miValoracionMaterias2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JDialog dialogo = new JDialog();
				dialogo.setResizable(false);
				dialogo.setTitle("Gestion de notas alternativo");
				dialogo.setBounds(100, 100, 650, 450);
				dialogo.setContentPane(new PanelGestionValoracionMateria2());
				dialogo.setModal(true);
				dialogo.setVisible(true);
				
			}
		});
        
        JMenuItem miPanelTablaNotas = new JMenuItem("Tabla Estudiantes");
        miPanelTablaNotas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JDialog dialogo = new JDialog();
				dialogo.setResizable(false);
				dialogo.setTitle("Notas del curso");
				dialogo.setBounds(100, 100, 610, 600);
				dialogo.setContentPane(new PanelTablaNotas());
				dialogo.setModal(true);
				dialogo.setVisible(true);
				
			}
		});
        
        JMenuItem miPanelHTML = new JMenuItem("Tabla HTML");
        miPanelHTML.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JDialog dialogo = new JDialog();
				dialogo.setResizable(false);
				dialogo.setTitle("Notas del curso");
				dialogo.setBounds(100, 100, 1000, 600);
				dialogo.setContentPane(new PanelTablaHTML());
				dialogo.setModal(true);
				dialogo.setVisible(true);
				
			}
		});
        
        menuArchivo.add(miCursoAcademico);
        menuArchivo.add(miMaterias);
        menuArchivo.add(miEstudiantes);
        menuArchivo.add(miProfesores);
        menuArchivo.add(miValoracionMaterias);
        menuArchivo.add(miValoracionMaterias2);
        menuArchivo.add(miPanelTablaNotas);
        menuArchivo.add(miPanelHTML);

        
        this.add(menuArchivo);
	}
	
}
