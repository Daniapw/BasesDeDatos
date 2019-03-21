import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import com.mysql.jdbc.Statement;

public class InsercionDeDatos {

	private static String tablas[] = new String[] {"etapa", "ciclista"};
	private static String nombres[] = new String[] {"Eva", "Juan", "Carmen", "Pablo", "Rafa", "Pilar", "Pedro", 
			"Lola", "Casimiro", "Gertrudis", "Eustaquio", "Gerarda", "Nepomunosio", "Argimira", "Ascensio", "Baltasara", "Baudilio", "Bernabea"};
	private static String apellidos[] = new String[] {"Gonzalez", "Lopez", "Gutierrez", "Ruiz", "Jurado", "Carrasco", "Flores", 
			"Sanchez", "Bose", "Martin", "Martinez", "Santos", "Pozo", "Quijano", "Romero", "Pisano", "Cuevas", "Sanz"};
	private static String nombresEquipos[] = new String[] {"Banesto", "Movistar Team", "Team Sky", "Team EF", "Nova Vita", "Team A", "Team B"};
	private static String localidades[] = new String[] {"Lucena", "Cabra", "Priego de Cordoba", "Puente Genil", "Benameji", "Palenciana","Baena","Albendin"};
	private static int REGISTROS_A_INSERTAR_EN_ETAPA= 10;
	private static int REGISTROS_A_INSERTAR_EN_CICLISTA = 100;
	
	//La variable CONT_NOTIFICACION_INSERCION controla cada cuantos registros saldra un
	//mensaje en la consola
	private static int CONT_NOTIFICACION_INSERCION = 10;
	
	//Si el boolean LOG esta a true se mostrara el progreso en la consola
	private static boolean LOG = true;
	
	
	
	/**
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	private static void vaciarTablas (Connection conn) throws SQLException {
		Statement s = (Statement) conn.createStatement();
		
		if (LOG)
			System.out.println("Eliminando los registros de todas las tablas");
		
		for (String tabla : tablas) {
			int registrosAfectados = s.executeUpdate("delete from ciclistas." + tabla);
			if (LOG)
				System.out.println("\t" + registrosAfectados + " registros eliminados en la tabla " + tabla);
		}
		s.close();
	}
	
	
	/**
	 * @throws ImposibleConectarException 
	 * @throws SQLException 
	 * 
	 */
	private static void creacionDatosCiclista (Connection conn) throws SQLException, ImposibleConectarException {
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement("INSERT INTO ciclistas.ciclista (dorsal, nombre, edad, nomeq) " +
					"VALUES  (?, ?, ?, ?)");
		
		int registrosInsertados;
		int contRegistrosInsertados = 0;		
		
		if (LOG)
			System.out.println("\nInsertando registros de en la tabla ciclista");

		for (int i = 0; i < REGISTROS_A_INSERTAR_EN_CICLISTA; i++) {
			
			ps.setInt(1,nextIdEnTabla(conn, "ciclista"));
			
			ps.setString(2, getStringAlAzar(nombres) + " " + getStringAlAzar(apellidos));
			
			ps.setInt(3, Utils.obtenerNumeroAzar(20, 35));
			
			ps.setString(4, getStringAlAzar(nombresEquipos));
			
			registrosInsertados = ps.executeUpdate();
			
			if (registrosInsertados != 1) {
				throw new SQLException ("No ha sido posible la inserci�n en ciclista");
			}
			
			contRegistrosInsertados++;
			
			if (contRegistrosInsertados % CONT_NOTIFICACION_INSERCION == 0 && LOG) {
				System.out.println("\t" + contRegistrosInsertados + " registros insertados en tabla ciclistas");
			}
		}
		
		ps.close();
	}
	
	/**
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	private static void creacionEtapas(Connection conn) throws SQLException{
		
		Statement s = (Statement) conn.createStatement();
		int registrosInsertados;
		int contRegistrosInsertados = 0;
		
		if (LOG)
			System.out.println("\nInsertando registros de en la tabla etapa");
		
		for (int i = 0; i < REGISTROS_A_INSERTAR_EN_ETAPA; i++) {
			
			int netapa = nextIdEnTabla(conn, "etapa");
			
			int km = Utils.obtenerNumeroAzar(1, 200);
			
			String salida = getStringAlAzar(localidades);
			
			String llegada = getStringAlAzar(localidades);
			
			int dorsal = getIdAzarEnTabla(conn, "ciclista");
			
			String sql= "INSERT INTO ciclistas.etapa (netapa, km, salida, llegada, dorsal) " 
					+"VALUES(" + netapa + ", '" + km + "', '" + salida + "', '" + llegada + "', '" + dorsal + "')";
			
			registrosInsertados = s.executeUpdate(sql);
			if (registrosInsertados != 1) {
				throw new SQLException ("No ha sido posible la inserci�n con la cadena:\n" + sql);
			}
			
			contRegistrosInsertados++;
			
			if (contRegistrosInsertados % CONT_NOTIFICACION_INSERCION == 0 && LOG) {
				System.out.println("\t" + contRegistrosInsertados + " registros insertados en tabla ciclistas");
			}
		}
		
		s.close();
		
	}
	
	
	/**
	 * 
	 * @param conn
	 * @param tabla
	 * @return
	 * @throws SQLException
	 */
	private static int getIdAzarEnTabla (Connection conn, String tabla) throws SQLException {
		int maxId = maxIdEnTabla(conn, tabla);
		int idAzar;
		
		Statement s = (Statement) conn.createStatement();
		ResultSet rs;
		do {
			idAzar = Utils.obtenerNumeroAzar(1, maxId);
			rs = s.executeQuery("select * from ciclistas." + tabla + " where dorsal = " + idAzar);
		} while (!rs.next());
		
		rs.close();
		s.close();
		return idAzar;
	}


	
	/**
	 * 
	 * @param conn
	 * @param tabla
	 * @return
	 * @throws SQLException
	 */
	private static ArrayList<Integer> getIdsEnTabla (Connection conn, String tabla) throws SQLException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		Statement s = (Statement) conn.createStatement();
		ResultSet rs;
		rs = s.executeQuery("select dorsal from ciclistas." + tabla + " order by dorsal");
		
		while (rs.next()) {
			ids.add(rs.getInt(1));
		}
		
		rs.close();
		s.close();
		return ids;
	}

	/**
	 * 
	 * @param strings
	 * @return
	 */
	private static String getStringAlAzar (String strings[]) {
		return strings[Utils.obtenerNumeroAzar(0, strings.length - 1)];
	}
	
	/**
	 * 
	 * @param conn
	 * @param tabla
	 * @return
	 * @throws SQLException
	 */
	
	private static int nextIdEnTabla (Connection conn, String tabla) throws SQLException {
		return maxIdEnTabla(conn, tabla) + 1;
	}
	
	
	/**
	 * 
	 * @param conn
	 * @param tabla
	 * @return
	 * @throws SQLException
	 */
	private static int maxIdEnTabla (Connection conn, String tabla) throws SQLException {
		Statement s = (Statement) conn.createStatement();

		String sql = "select max(dorsal) from ciclistas." + tabla;
		ResultSet rs = s.executeQuery(sql);
		int max = 1; 
		if (rs.next() ) {
			max = rs.getInt(1);
		}
		rs.close();
		s.close();
		return max;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			long startTime = new Date().getTime();
			
			Connection conn;
			conn = ConnectionManagerV2.getConexion();
			
			// Limpieza en las tablas
			vaciarTablas(conn);
			//Insertar registros en ciclista
			creacionDatosCiclista(conn);
			//Insertar datos en etapa
			//creacionEtapas(conn);
			
			long usedMillis = new Date().getTime() - startTime;
			int secs = (int) (usedMillis/1000);
			System.out.println("\nProceso terminado en " + secs + " segundos y " + (usedMillis - secs * 1000) + " milisegundos");
			
		} catch (SQLException | ImposibleConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
