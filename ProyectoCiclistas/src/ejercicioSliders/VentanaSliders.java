package ejercicioSliders;

import java.awt.Color;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VentanaSliders extends JFrame {
	
	JSlider red = new JSlider(0, 255, 00);
	JSlider green = new JSlider(0, 255, 00);
	JSlider blue = new JSlider(0, 255, 00);
	JPanel panel2 = new JPanel();
	
	
	
	/**
	 * 
	 */
	public VentanaSliders() {
		
		super("Ejercicio sliders");
		
		setDimensionesBasicas();
		
		this.add(getPanelSliders());
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * 
	 * @return
	 */
	private JPanel getPanelSliders() {
		
		JPanel panel = new JPanel();
		
		panel.add(new JLabel("Red"));
		panel.add(red);
		
		panel.add(new JLabel("Green"));
		panel.add(green);
		
		panel.add(new JLabel("Blue"));
		panel.add(blue);
		
		configuraSlider(red);
		configuraSlider(green);
		configuraSlider(blue);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(panel2);
		
		return panel;
		
	}
	
	/**
	 * Metodo para establecer las dimensiones del JFrame
	 */
	private void setDimensionesBasicas () {
		this.setExtendedState(JFrame.NORMAL);
		this.setBounds(0, 0, 700, 500);

	}
	
	/**
	 * Metodo que configura los sliders
	 * @param slider
	 */
	private void configuraSlider(JSlider slider) {
		
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(5);
		
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setPaintTrack(true);
		
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if (red.getValue() > 0 || green.getValue() > 0 || blue.getValue() > 0) {
					
					StringBuffer valor = new StringBuffer("");
					
					valor.append(configuraStringBuffer(red.getValue()));
					valor.append(configuraStringBuffer(green.getValue()));
					valor.append(configuraStringBuffer(blue.getValue()));
					
					System.out.println("#" + valor);
					
					panel2.setBackground(Color.decode("#" + valor.toString()));
					
					panel2.repaint();
				}
				
			}
		});
	}
	
	/**
	 * 
	 * @param valorColor
	 * @return
	 */
	private String configuraStringBuffer(int valorColor) {
		
		String valor = Integer.toHexString(valorColor);
		
		if (valor.length() < 2) {
			
			valor = "0" + valor;
			
		}
		
		return valor;
		
	}
}
