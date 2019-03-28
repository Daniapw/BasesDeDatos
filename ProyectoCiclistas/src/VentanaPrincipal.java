
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
	public JTextField tfID = new JTextField();
	public JTextField tfCIF = new JTextField();
	public JTextField tfNombre = new JTextField();
	
	
	/**
	 * Constructor
	 */
	public VentanaPrincipal () {
		super ("Primera gestion fabricante");
		
		// Establecer tamano por defecto del JFrame
		setDimensionesBasicas();

		// Construccion del JPanel
		this.setContentPane(getPanelPrincipal());
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	/**
	 * Metodo para crear el JPanel
	 * @return
	 */
	private JPanel getPanelPrincipal() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.lightGray);
		
		//Establecer layout
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(getPanelID());
		panel.add(getPanelCIF());
		panel.add(getPanelNombre());
		panel.add(getPanelBotonGuardar());
		
		return panel;
	}
	
	/**
	 * Panel ID
	 * @return
	 */
	private JPanel getPanelID() {
		
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.YELLOW);
		
		JLabel label = new JLabel("ID:");
		
		
		tfID.setColumns(20);
		
		panel.add(label);
		panel.add(tfID);
		
		return panel;
	}
	
	/**
	 * Panel CIF
	 * @return
	 */
	private JPanel getPanelCIF() {
		
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.RED);
		
		JLabel label = new JLabel("CIF:");
		
		tfCIF.setColumns(20);
		
		panel.add(label);
		panel.add(tfCIF);
		
		return panel;
	}
	
	/**
	 * Panel nombre
	 * @return
	 */
	private JPanel getPanelNombre() {
		
		JPanel panel = new JPanel();

		panel.setBackground(Color.GREEN);
		
		JLabel label = new JLabel("Nombre:");
		
		tfNombre.setColumns(20);
		
		panel.add(label);
		panel.add(tfNombre);
		
		return panel;
	}
	
	/**
	 * Panel boton
	 * @return
	 */
	private JPanel getPanelBotonGuardar() {
		
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.ORANGE);
		
		JButton boton = new JButton("Guardar");
		JButton botonDelete = new JButton("Eliminar");
		
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				guardarRegistro();
				
			}
		});
		
		botonDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				eliminarRegistro();
				
			}
		});
		
		panel.add(boton);
		panel.add(botonDelete);
		
		return panel;
	}
	
	/**
	 * Metodo para establecer las dimensiones del JFrame
	 */
	private void setDimensionesBasicas () {
		this.setExtendedState(JFrame.NORMAL);
		this.setBounds(0, 0, 200, 200);

	}
	
	/**
	 * Metodo que invocara el boton guardar
	 */
	private void guardarRegistro() {
		
		try {
			//Se obtiene la connection del ConnectionManagerV2, que las extrae de la pool de conexiones
			//de Oracle, UCP
			Connection cn = ConnectionManagerV2.getConexion();
			
			//Se crea el PreparedStatement para comprobar si la ID introducida ya existe
			PreparedStatement ps = cn.prepareStatement(
					"SELECT id from tutorialjavacoches.fabricante WHERE id = ?");
			
			ps.setInt(1, Integer.parseInt(tfID.getText()));
			
			
			ResultSet rs = ps.executeQuery();
			
			//Si la ID existe el ResultSet NO estara vacio, por lo que habra que actualizar
			//el registro existente cuya ID es igual a la ID introducida
			if (rs.next()) {
				
				actualizar();
				
			}
			//Si no existe se inserta un nuevo registro con los datos introducidos
			else {
				
				insertar();
				
			}
			
			
		} catch (SQLException | ImposibleConectarException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * Metodo para insertar nuevos registros en la base de datos
	 */
	private void insertar() {
		
		try {
			Connection cn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = cn.prepareStatement("INSERT INTO tutorialjavacoches.fabricante (id, cif, nombre)"
					+ " VALUES(?, ?, ?)");
			
			ps.setInt(1, Integer.parseInt(tfID.getText()));
			ps.setString(2, tfCIF.getText());
			ps.setString(3, tfNombre.getText());
			
			int filasAfectadas = ps.executeUpdate();
			
			if (filasAfectadas==1) {
				
				System.out.println("Registro insertado");
				
			}
			
			ps.close();
			cn.close();
			
			JOptionPane.showMessageDialog(null,"Registro guardado");
			
		} catch (SQLException | ImposibleConectarException e ) {
			
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Metodo para insertar nuevos registros en la base de datos
	 */
	private void actualizar() {
		
		try {
			Connection cn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = cn.prepareStatement("UPDATE tutorialjavacoches.fabricante SET cif = ?, nombre = ? "
					+ "WHERE id = ?");
			
			
			ps.setString(1, tfCIF.getText());
			ps.setString(2, tfNombre.getText());
			ps.setInt(3, Integer.parseInt(tfID.getText()));
			
			int filasAfectadas = ps.executeUpdate();
			
			if (filasAfectadas==1) {
				
				System.out.println("Registro modificado");
				
			}
			
			ps.close();
			cn.close();
			
			JOptionPane.showMessageDialog(null,"Registro existente actualizado");
			
		} catch (SQLException | ImposibleConectarException e ) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para eliminar un registro
	 */
	private void eliminarRegistro() {
		
		try {
			//Se obtiene la connection del ConnectionManagerV2, que las extrae de la pool de conexiones
			//de Oracle, UCP
			Connection cn = ConnectionManagerV2.getConexion();
			
			//Se crea el PreparedStatement para comprobar si la ID introducida ya existe
			PreparedStatement ps = cn.prepareStatement(
					"SELECT id from tutorialjavacoches.fabricante WHERE id = ?");
			
			ps.setInt(1, Integer.parseInt(tfID.getText()));
			
			
			ResultSet rs = ps.executeQuery();
			
			//Si la ID existe el ResultSet NO estara vacio, por lo que habra que actualizar
			//el registro existente cuya ID es igual a la ID introducida
			if (rs.next()) {
				
				PreparedStatement psBorrar = cn.prepareStatement("DELETE FROM tutorialjavacoches.fabricante WHERE id = ?");
				psBorrar.setInt(1, Integer.parseInt(tfID.getText()));
				
				psBorrar.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "El registro ha sido eliminado");
				
			}
			//Si no existe se inserta un nuevo registro con los datos introducidos
			else {
				
				JOptionPane.showMessageDialog(null, "El registro no existe");
				
			}
			
			
		} catch (SQLException | ImposibleConectarException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
}
