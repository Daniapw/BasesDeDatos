package gui;

import javax.swing.JPanel;
import javax.swing.JEditorPane;
import java.awt.BorderLayout;

public class PanelTablaNotas extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelTablaNotas() {
		setLayout(new BorderLayout(0, 0));
		
		JEditorPane jEditorPane = new JEditorPane();
		add(jEditorPane, BorderLayout.CENTER);
		
		jEditorPane.setContentType("text/html");
		jEditorPane.setText("<b>hola</b><br>" + "<i>adios</i><br>" + "<font face=\"arial\">fuente arial</font><br>"
				+ "<font face=\"courier\">fuente courier</font><br>" + "<font size=\"24\">fuente grande</font><br>"
				+ "<font color=\"red\">color rojo</font><br>"
				+ "<img src=\"http://webdidacticarafaelmunoz.appspot.com/java/CursodeSpaceInvaders/res/nave.gif\"></img>");
	}

}
