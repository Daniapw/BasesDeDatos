import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConexionBasica {

	public static void main(String[] args) {
		conexion();

	}

	/**
	 * Conexion a base de datos 'ciclistas'
	 */
	private static void conexion() {
		
		String driver = Propiedades.getProperty("JDBC_DRIVER_CLASS");
		String user = Propiedades.getProperty("JDBC_USER");
		String password = Propiedades.getProperty("JDBC_PASSWORD");
		String host = Propiedades.getProperty("JDBC_HOST");
		String schema = Propiedades.getProperty("JDBC_SCHEMA_NAME");
		
		
		try {
			// A trav�s de la siguiente l�nea comprobamos si tenemos acceso al driver MySQL, si no fuera as�
			// no podemos trabajar con esa BBDD.
			Class.forName(driver);
		   
			// Necesitamos obtener un acceso a la BBDD, eso se materializa en un objeto de tipo Connection, al cual
			// le tenemos que pasar los par�metros de conexi�n.
			Connection conexion = (Connection) DriverManager.getConnection ("jdbc:mysql://" + host + "/" + schema, user, password);
		   
			// Para poder ejecutar una consulta necesitamos utilizar un objeto de tipo Statement
			Statement s = (Statement) conexion.createStatement(); 
			
			// La ejecuci�n de la consulta se realiza a trav�s del objeto Statement y se recibe en forma de objeto
			// de tipo ResultSet, que puede ser navegado para descubrir todos los registros obtenidos por la consulta
			ResultSet rs = s.executeQuery ("select * from ciclista where nombre like 'M%'");
		   
			// Navegaci�n del objeto ResultSet
			while (rs.next()) { 
				System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getString(3) + " " + rs.getString(4)); 
			}
			
			// Cierre de los elementos
			rs.close();
			s.close();
			conexion.close();
		}
		catch (ClassNotFoundException ex) {
			System.out.println("Imposible acceder al driver Mysql");
		}
		catch (SQLException ex) {
			System.out.println("Error en la ejecuci�n SQL: " + ex.getMessage());
		}
	}
	
		
		
}

