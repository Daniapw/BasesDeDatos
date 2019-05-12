package gui;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import gui.utils.CacheImagenes;
import modelo.Estudiante;
import modelo.controladores.EstudianteControlador;

public class PanelTablaNotas extends JPanel {
	private JTable jTableEstudiantes;
	private PanelGestionEstudiantes panelEstudiantes = new PanelGestionEstudiantes();
	private DefaultTableModel dtm = null;
	private Object datosEnTabla[][] = DatosEstudiantes.getDatosDeTabla();
	private String titulosEnTabla[] = DatosEstudiantes.getTitulosColumnas();
	private JToolBar toolBar;

	/**
	 * Create the panel.
	 */
	public PanelTablaNotas() {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);
		toolBar = getToolbar();
		this.add(toolBar,BorderLayout.NORTH);
		
		
		JScrollPane jScrollPane = new JScrollPane();
		splitPane.setLeftComponent(jScrollPane);
		
		dtm = getDefaultTableModelNoEditable();
		jTableEstudiantes = new JTable(dtm);
		jScrollPane.setViewportView(jTableEstudiantes);
		
		panelEstudiantes.remove(panelEstudiantes.toolBar);
		splitPane.setRightComponent(panelEstudiantes);

		jTableEstudiantes.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				super.mouseClicked(arg0);
				seleccionarEstudiante();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				seleccionarEstudiante();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				seleccionarEstudiante();
			}

			/**
			 * Metodo que llama a cargarDatosEstudianteEspecifico
			 */
			public void seleccionarEstudiante() {
				int filaSelec = jTableEstudiantes.getSelectedRow();
				Integer id = (Integer) jTableEstudiantes.getValueAt(filaSelec, 0);
				panelEstudiantes.cargarDatosEstudianteEspecifico(id);
			}
			
		});
		
		jTableEstudiantes.setRowSelectionInterval(0, 0);
		
	}

	
	/**
	 * Metodo para coger el DefaultTableModel
	 * @return
	 */
	private DefaultTableModel getDefaultTableModelNoEditable () {
		@SuppressWarnings("serial")
		DefaultTableModel dtm = new DefaultTableModel(datosEnTabla, titulosEnTabla) {
			
			/**
			 * La sobreescritura de este método nos permite controlar qué celdas queremos que sean editables
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		return dtm;
	}
	
	/**
	 * 
	 * @return
	 */
	public JToolBar getToolbar() {
		
		toolBar = new JToolBar();
		
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
					obtenido = EstudianteControlador.getInstancia().findPrevious(panelEstudiantes.actual);
				if (funcion == PanelGenerico.LOAD_NEXT) 
					obtenido = EstudianteControlador.getInstancia().findNext(panelEstudiantes.actual);
				if (funcion == PanelGenerico.LOAD_LAST) 
					obtenido = EstudianteControlador.getInstancia().findLast();
				if (funcion == PanelGenerico.NEW) 
					panelEstudiantes.nuevo();
				if (funcion == PanelGenerico.SAVE) 
					panelEstudiantes.guardar();
				if (funcion == PanelGenerico.REMOVE) 
					obtenido = panelEstudiantes.eliminar();
				
				if (obtenido != null) {
					panelEstudiantes.actual = obtenido;
					panelEstudiantes.cargarDatosActual();
				}
				
				
				//Seleccionar fila correspondiente
				Integer id = panelEstudiantes.actual.getId();
				
				for (int i = 0; i < dtm.getRowCount(); i++) {
					
					if (dtm.getValueAt(i, 0) == id) {
						jTableEstudiantes.setRowSelectionInterval(i, i);
					}
					
				}
				
			}});
	}
	
}
