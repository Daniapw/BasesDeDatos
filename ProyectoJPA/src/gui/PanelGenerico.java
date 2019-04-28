package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import gui.utils.CacheImagenes;
import modelo.TipologiaSexo;
import modelo.controladores.TipologiaSexoControlador;

public class PanelGenerico extends JPanel {


	protected JTextField jtfID = new JTextField(20);
	protected JTextField jtfNombre = new JTextField(20);
	protected JTextField jtfPrimerApellido = new JTextField(20);
	protected JTextField jtfSegundoApellido = new JTextField(20);
	protected JComboBox<TipologiaSexo> jcbSexo = new JComboBox<TipologiaSexo>();
	protected JTextField jtfDNI = new JTextField(20);
	protected JTextField jtfDireccion = new JTextField(20);
	protected JTextField jtfEmail = new JTextField(20);
	protected JTextField jtfTelefono= new JTextField(20);
	protected JScrollPane scroll = new JScrollPane();
	protected JButton abrirChooser = new JButton("Cargar imagen...");
	protected JFileChooser chooser;
	protected JTextField jtfColorPreferido = new JTextField(20);
	protected JColorChooser jColorChooser;
	protected JButton abrirColorChooser = new JButton();
	private byte[] imagen;
	private JPopupMenu jPopUp = new JPopupMenu();
	protected GridBagConstraints gbc = new GridBagConstraints();
	
	private JPanel panel = new JPanel();

	
	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;
	
	/**
	 * 
	 */
	public PanelGenerico() {
		super();
		this.setLayout(new BorderLayout());
		this.add(getPanelCentral(), BorderLayout.CENTER);
		

	}
	
	/**
	 * 
	 * @return
	 */
	private JPanel getPanelCentral() {
		
		panel.setLayout(new GridBagLayout());

		double pesoCol1 = 0.15, pesoCol2 = 0.2;

		gbc.insets = new Insets(5, 5, 5, 5);
		
		//ID
		colocaComponente(0, 0, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NORTH);
		panel.add(new JLabel("ID:"), gbc);
		
		colocaComponente(1, 0, GridBagConstraints.WEST, pesoCol2, 0, GridBagConstraints.NORTH);
		panel.add(jtfID, gbc);
		
		jtfID.setEnabled(false);

		//Nombre
		colocaComponente(0, 1, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NORTH);
		panel.add(new JLabel("Nombre:"), gbc);
		
		colocaComponente(1, 1, GridBagConstraints.WEST, pesoCol2, 0, GridBagConstraints.NORTH);
		panel.add(jtfNombre, gbc);

		//Apellido1
		colocaComponente(0, 2, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NORTH);
		panel.add(new JLabel("Primer apellido:"), gbc);
		
		colocaComponente(1, 2, GridBagConstraints.WEST, pesoCol2, 0, GridBagConstraints.NORTH);
		panel.add(jtfPrimerApellido, gbc);
		
		//Apellido2
		colocaComponente(0, 3, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NORTH);
		panel.add(new JLabel("Segundo apellido:"), gbc);
		
		colocaComponente(1, 3, GridBagConstraints.WEST, pesoCol2, 0, GridBagConstraints.NORTH);
		panel.add(jtfSegundoApellido, gbc);
		
		//Sexo
		colocaComponente(0, 4, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NORTH);
		panel.add(new JLabel("Sexo:"), gbc);
		
		colocaComponente(1, 4, GridBagConstraints.WEST, pesoCol2, 0, GridBagConstraints.NORTH);
		panel.add(jcbSexo, gbc);
		
		inicializarCBSexo();
		
		//DNI
		colocaComponente(0, 5, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NORTH);
		panel.add(new JLabel("DNI:"), gbc);
		
		colocaComponente(1, 5, GridBagConstraints.WEST, pesoCol2, 0, GridBagConstraints.NORTH);
		panel.add(jtfDNI, gbc);
		
		//Direccion
		colocaComponente(0, 6, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NORTH);
		panel.add(new JLabel("Direccion:"), gbc);
		
		colocaComponente(1, 6, GridBagConstraints.WEST, pesoCol2, 0, GridBagConstraints.NORTH);
		panel.add(jtfDireccion, gbc);
		
		//Email
		colocaComponente(0, 7, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NORTH);
		panel.add(new JLabel("Email:"), gbc);
		
		colocaComponente(1, 7, GridBagConstraints.WEST, pesoCol2, 0, GridBagConstraints.NORTH);
		panel.add(jtfEmail, gbc);
		
		//Telefono
		colocaComponente(0, 8, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NORTH);
		panel.add(new JLabel("Telefono:"), gbc);
		
		colocaComponente(1, 8, GridBagConstraints.WEST, pesoCol2, 0, GridBagConstraints.NORTH);
		panel.add(jtfTelefono, gbc);
		
		//Color preferido
		colocaComponente(0, 9, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NORTH);
		panel.add(new JLabel("Color preferido:"), gbc);
		
		colocaComponente(1, 9, GridBagConstraints.WEST, pesoCol2, 0, GridBagConstraints.NORTH);
		panel.add(jtfColorPreferido, gbc);

		colocaComponente(2, 9, GridBagConstraints.WEST, pesoCol2, 0, GridBagConstraints.WEST);
		panel.add(abrirColorChooser, gbc);

		abrirColorChooser.setIcon(CacheImagenes.getCacheImagenes().getIcono("iconoColores.png"));
		abrirColorChooser.setPreferredSize(new Dimension(33,33));
		
		abrirColorChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				seleccionarColor();
			}
		});

		//Panel Scroll Imagen
		scroll.setPreferredSize(new Dimension(150,150));
		colocaComponente(2, 1, GridBagConstraints.WEST, 0.5,0, GridBagConstraints.NORTH);
		panel.add(scroll, gbc);
		
		//Boton Cargar Imagen
		colocaComponente(2, 2, GridBagConstraints.WEST, pesoCol2,0, GridBagConstraints.NORTH);
		
		abrirChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				seleccionarImagen();
			}
		});
		
		panel.add(abrirChooser, gbc);
		
		//Menu contextual
		configurarPopup();
		scroll.addMouseListener(new MouseAdapter() {
			
           @Override
            public void mouseClicked(MouseEvent e) {
                showPopup(e);
            }
 
            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }
	 
            /**
             *
             * @param e
             */
            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    jPopUp.show(e.getComponent(),e.getX(), e.getY());
                }
            }
			
		} ); 
				
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
		gbc.gridx = gridX;
		gbc.gridy = gridY;
		gbc.anchor = anchor;
		gbc.weightx = pesoColumna;
		gbc.weighty = pesoFila;
		gbc.fill = fill;
	}

	/**
	 * Metodo para inicalizar la JCB del sexo
	 */
	private void inicializarCBSexo() {
		
		List<TipologiaSexo> sexos = TipologiaSexoControlador.getInstancia().findAllTipologiaSexos();
		
		for (TipologiaSexo sexo:sexos) {
			jcbSexo.addItem(sexo);	
		}
		
	}

	/**
	 * Metodo para el JFileChooser
	 */
	private void seleccionarImagen() {
		
		this.chooser = new JFileChooser();
		
		chooser.setCurrentDirectory(new File("C:\\"));
		
		this.chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		// Filtro del tipo de ficheros que puede abrir
		this.chooser.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return "Archivos de imagen *.gif, *.png, *.jpg";
			}
			
			@Override
			public boolean accept(File f) {
				if ((f.isFile() && (f.getAbsolutePath().endsWith(".gif") || 
						f.getAbsolutePath().endsWith(".png") ||
						f.getAbsolutePath().endsWith(".jpg") ))) 
					
					return true;
				
				return false;
			}
		});
		
		// Dialogo para la elecci�n del usuario
		int seleccionUsuario = chooser.showOpenDialog(null);
		
		if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
			File fichero = this.chooser.getSelectedFile();
			
			if (fichero.isFile()) {
				try {
					byte[] imagenEnBytes = Files.readAllBytes(fichero.toPath());
					this.imagen = imagenEnBytes;
					cambiarImagen(imagen);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}
	
	/**
	 * Metodo para el JColorChooser
	 */
	private void seleccionarColor() {
		
		Color color = this.jColorChooser.showDialog(null, "Seleccione un color", Color.gray);
		
		if (color != null) {
			
			String strColor = "#"+Integer.toHexString(color.getRGB()).substring(2);
			this.jtfColorPreferido.setText(strColor);
			panel.setBackground(color);
			panel.repaint();
		}
		
	}
	
	
	/**
	 * Metodo para cambiar la imagen a la del registro actual
	 * @param imagenAMostrar
	 */
	protected void cambiarImagen(byte[] imagenAMostrar) {		
		//Cargar imagen de Longblob
		JLabel lblImagen = new JLabel();
		if (imagenAMostrar != null) {
			ImageIcon icono = new ImageIcon(imagenAMostrar);
			lblImagen.setIcon(icono);
		}
		else {
			lblImagen.setIcon(CacheImagenes.getCacheImagenes().getIcono("SinImagen.png"));	
		}
		
		scroll.setViewportView(lblImagen);
	}
	
	/**
	 * Metodo para cambiar el color cuando se cambie de registro
	 * @param colorActual
	 */
	protected void cambiarColor(String colorActual) {
		
		this.jtfColorPreferido.setText(colorActual);
		
		if (!colorActual.equals("")) 
			panel.setBackground(Color.decode(colorActual));
		else 
			panel.setBackground(Color.white);
		
		panel.repaint();

	}
	
	/**
	 * Metodo para configurar el JPopupMenu
	 */
	private void configurarPopup() {
		
		//Dimensiones
		JMenuItem miDimensiones = new JMenuItem("Dimensiones: ");
		jPopUp.add(miDimensiones);
		
		jPopUp.addSeparator();
		
		//Cambiar imagen
		JMenuItem miCambiarImagen = new JMenuItem("Cambiar imagen");
		miCambiarImagen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				seleccionarImagen();			
			}
		});
		
		jPopUp.add(miCambiarImagen);
	}
	
	/**
	 * Getters y setters
	 * @return
	 */
	
	public JTextField getJtfID() {
		return jtfID;
	}

	public JTextField getJtfNombre() {
		return jtfNombre;
	}

	public JTextField getJtfPrimerApellido() {
		return jtfPrimerApellido;
	}

	public JTextField getJtfSegundoApellido() {
		return jtfSegundoApellido;
	}

	public JTextField getJtfDNI() {
		return jtfDNI;
	}

	public JTextField getJtfDireccion() {
		return jtfDireccion;
	}

	public JTextField getJtfEmail() {
		return jtfEmail;
	}

	public JTextField getJtfTelefono() {
		return jtfTelefono;
	}
	
	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getJtfColorPreferido() {
		return jtfColorPreferido.getText();
	}

	public void setJtfColorPreferido(String jtfColorPreferido) {
		this.jtfColorPreferido.setText(jtfColorPreferido);;
	}
	
	
}
