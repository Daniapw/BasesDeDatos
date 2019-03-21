import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Propiedades {

	private static Properties propiedades = null;
	
	public Propiedades() {
		
		super();
		
	}
	
	/**
	 * Singleton
	 * @return
	 */
	private static Properties getPropiedades() {
		
		if (propiedades == null) {
			
			propiedades = new Properties();
			
			try {
				File file = new File("src/propiedades.properties");
				propiedades.load(new FileReader(file));
				
			} catch (FileNotFoundException e) {

				System.out.println("No se ha encontrado el archivo");	
				e.printStackTrace();
			}catch (IOException e) {
				
				System.out.println("Excepcion I/O");
				e.printStackTrace();
			}
			
		}
		
		return propiedades;
		
	}
	
	/**
	 * Coger propiedad String
	 * @param nombre
	 * @return
	 */
	public static String getProperty(String nombre) {
		
		return getPropiedades().getProperty(nombre);
		
	}
}

