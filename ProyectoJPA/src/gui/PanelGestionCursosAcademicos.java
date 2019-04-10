package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import gui.utils.CacheImagenes;


public class PanelGestionCursosAcademicos extends JPanel {

	JTextField jtfID = new JTextField();
	JTextField jtfDesc = new JTextField();
	GridBagConstraints gridBagConstraints = new GridBagConstraints();
	
	public PanelGestionCursosAcademicos() {
		super();
		this.setLayout(new BorderLayout());
		
		this.add(toolbar(), BorderLayout.NORTH);
		this.add(getPanelCentral());
	}
	
	public JToolBar toolbar() {
		
		JToolBar toolbar = new JToolBar();
		
		toolbar.add(creaBoton("Nuevo", "nuevo.png", "Crear nuevo registro", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Hola");
				
			}
		}));
		
		toolbar.add(creaBoton("Guardar", "guardar.png", "Guardar registro", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		}));
		
		
		toolbar.add(creaBoton("Eliminar", "eliminar.png", "Eliminar registro", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		}));
		

		toolbar.addSeparator();
		
		toolbar.add(creaBoton("Primero", "gotostart.png", "Ir al primer registro", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		}));
		
		toolbar.add(creaBoton("Anterior", "previous.png", "Ir al anterior registro", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		}));
		
		toolbar.add(creaBoton("Siguiente", "next.png", "Ir al siguiente registro", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		}));
		
		toolbar.add(creaBoton("Último", "gotoend.png", "Ir al último registro", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		}));


		return toolbar;
	}
	
	/**
	 * 
	 * @param titulo
	 * @param icono
	 * @return
	 */
	private JButton creaBoton(String titulo, String icono, String toolTip, ActionListener listener) {
        JButton jbt = new JButton();
        
        jbt.setText(titulo);
        jbt.setToolTipText(toolTip);
        
        jbt.addActionListener(listener);
        
        try {
        	jbt.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));  
          } catch (Exception ex) {
        	  ex.printStackTrace();
          }
        return jbt;
	}
	
	private JPanel getPanelCentral() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		
		
		panel.add(new JLabel("ID"));
		panel.add(jtfID);
	
		panel.add(new JLabel("Descripcion"));
		panel.add(jtfDesc);
		
		return panel;
	}
	
	/**
	 * 
	 * @param gridX
	 * @param gridY
	 * @param pesoColumna
	 * @param pesoFila
	 * @param fill
	 */
	private void colocaComponente (int gridX, int gridY, int anchor, double pesoColumna, double pesoFila, int fill) {
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.anchor = anchor;
		gridBagConstraints.weightx = pesoColumna;
		gridBagConstraints.weighty = pesoFila;
		gridBagConstraints.fill = fill;
		
	}
}
