package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelTablaNotas extends JPanel {
	private JTable jTableEstudiantes;

	/**
	 * Create the panel.
	 */
	public PanelTablaNotas() {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);
		
		JScrollPane jScrollPane = new JScrollPane();
		splitPane.setLeftComponent(jScrollPane);
		
		jTableEstudiantes = new JTable();
		jScrollPane.setViewportView(jTableEstudiantes);
		
		PanelGestionEstudiantes panelEstudiantes = new PanelGestionEstudiantes();
		splitPane.setRightComponent(panelEstudiantes);
	}

}
