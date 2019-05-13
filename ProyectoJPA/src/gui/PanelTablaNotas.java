package gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.AbstractTableModel;
import gui.utils.CacheImagenes;
import modelo.Estudiante;
import modelo.controladores.EstudianteControlador;

public class PanelTablaNotas extends JPanel {
	private JTable jTableEstudiantes;
	private PanelGestionEstudiantes panelEstudiantes = new PanelGestionEstudiantes();
	private ModeloPersonalizado modelo = null;
	private JToolBar toolBar;
	private Object datosTabla[][] = null;

	/**
	 * Create the panel.
	 */
	public PanelTablaNotas() {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(150);
		add(splitPane, BorderLayout.CENTER);
		toolBar = getToolbar();
		this.add(toolBar,BorderLayout.NORTH);
		
		
		JScrollPane jScrollPane = new JScrollPane();
		splitPane.setLeftComponent(jScrollPane);
		
		//Creacion modelo
		modelo = new ModeloPersonalizado();
		
		//Puntero datos
		datosTabla = modelo.datos;
		jTableEstudiantes = new JTable(modelo);
		//Personalizar encabezado de la tabla
		jTableEstudiantes.getTableHeader().setFont(new Font(this.getFont().getFontName(), Font.BOLD, 12));
		jTableEstudiantes.getTableHeader().setBackground(Color.decode("#3281ff"));
		jTableEstudiantes.getTableHeader().setForeground(Color.WHITE);

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
	 * Modelo de tabla abstracto, que nos permite un control mayor sobre la apariencia, eventos y renderizadores de la tabla
	 * @author R
	 *
	 */
	private class ModeloPersonalizado extends AbstractTableModel {

		Object datos[][] = null;
		String titulos[] = null;

		/**
		 * 
		 */
		public ModeloPersonalizado() {
			// Datos a presentar en la tabla
			datos = DatosEstudiantes.getDatosDeTabla();
			titulos = DatosEstudiantes.getTitulosColumnas();
		}
		
		// Los tres siguientes m�todos son los m�nimos necesarios para representar la tabla
		/**
		 * Permite que la tabla sepa cu�ntas columnas debe mostrar
		 */
		@Override
		public int getColumnCount() {
			return titulos.length;
		}

		/**
		 * Permite que la tabla sepa cu�ntas filas debe mostrar
		 */
		@Override
		public int getRowCount() {
			return datos.length;
		}

		/**
		 * Se conocer� el dato en cada celda, es uno de los m�todos fundamentales del abstractTableModel
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return datos[rowIndex][columnIndex];
		}

		/**
		 * Podemos indicar si la tabla ser� o no editable en cada una de sus celdas, incluso por separado
		 */
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		/**
		 * Este m�todo da nombre a las columnas de la tabla
		 */
		@Override
		public String getColumnName(int column) {
			return this.titulos[column];
		}

		/**
		 * Permite que la tabla sepa que tipo de dato est� mostrando en cada columna
		 */
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if (this.datos.length > 0) {
				return this.datos[0][columnIndex].getClass();
			}
			return String.class;
		}

		/**
		 * Este m�todo s�lo debe implementarse si la tabla es editable y los datos pueden cambiar
		 */
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			this.datos[rowIndex][columnIndex] = aValue;
			fireTableCellUpdated(rowIndex, columnIndex);
		}
		
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
				if (funcion == PanelGenerico.SAVE) {
					panelEstudiantes.guardar();
					actualizarTabla();
				}
					
				if (funcion == PanelGenerico.REMOVE) {
					obtenido = panelEstudiantes.eliminar();
					//Puesto que al borrar un registro el numero de registros totales
					//va a cambiar llamo a rehacerTabla() directamente
					rehacerTabla();
				}
				
				if (obtenido != null) {
					panelEstudiantes.actual = obtenido;
					panelEstudiantes.cargarDatosActual();
				}
				
				
				//Seleccionar fila correspondiente
				Integer id = panelEstudiantes.actual.getId();
				
				for (int i = 0; i < modelo.getRowCount(); i++) {
					
					if (modelo.getValueAt(i, 0).equals(id)) {
						jTableEstudiantes.setRowSelectionInterval(i, i);
						break;
					}
					
				}
				
			}});
	}
	
	/**
	 * 
	 */
	private void actualizarTabla() {
		
		Estudiante actual = panelEstudiantes.actual;
		
		Object nuevosDatos[][] = DatosEstudiantes.getDatosDeTabla();
		
		//Si la longitud de los datos actualizados es mayor a la de los datos actuales significa que hay un nuevo
		//registro, por lo tanto hay que rehacer la tabla
		if (nuevosDatos.length > datosTabla.length) {
			rehacerTabla();
		}
		//Si la longitud es igual significa que un registro ha sido modificado
		else {
			
			for (int i=0; i < jTableEstudiantes.getRowCount(); i++) {
				Integer idFila = (Integer) modelo.getValueAt(i, 0);
				
				if (idFila == panelEstudiantes.actual.getId()) {
					
					modelo.setValueAt(actual.getNombre(), i, 1);
					modelo.setValueAt(actual.getPrimerApellido(), i, 2);
					modelo.setValueAt(actual.getSegundoApellido(), i, 3);
					modelo.setValueAt(actual.getDni(), i, 4);
					modelo.setValueAt(actual.getDireccion(), i, 5);
					modelo.setValueAt(actual.getEmail(), i, 6);
					modelo.setValueAt(actual.getImagen(), i, 7);
					modelo.setValueAt(actual.getColorPreferido(), i, 8);
					modelo.setValueAt(actual.getTipologiaSexo(), i, 9);
						
					break;
				}	
			}
				
		}
		
	}
	
	/**
	 * Metodo para rehacer la tabla con datos actualizados
	 */
	private void rehacerTabla() {		
		modelo = new ModeloPersonalizado();
		jTableEstudiantes.setModel(modelo);
	}
}
