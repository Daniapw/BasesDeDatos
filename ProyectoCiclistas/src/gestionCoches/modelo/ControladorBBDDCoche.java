package gestionCoches.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gestionCoches.modelo.entidades.Coche;

public class ControladorBBDDCoche {

	/**
	 * 
	 * @return
	 */
	public static Coche getPrimerCoche () {
		Coche coche = null;
		try {
			Connection conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM tutorialjavacoches.coche order by id limit 1");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				coche = getCocheFromResultSet(rs);		
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException | ImposibleConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return coche;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public static Coche getUltimoCoche () {
		Coche coche = null;
		try {
			Connection conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM tutorialjavacoches.coche order by id desc limit 1");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				coche = getCocheFromResultSet(rs);		
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException | ImposibleConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return coche;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public static Coche getSiguienteCoche (Coche cocheActual) {
		Coche cocheResultado = null;
		try {
			Connection conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM tutorialjavacoches.coche where id > ? order by id limit 1");
			ps.setInt(1, cocheActual.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cocheResultado = getCocheFromResultSet(rs);		
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException | ImposibleConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cocheResultado;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public static Coche getAnteriorCoche (Coche cocheActual) {
		Coche cocheResultado = null;
		try {
			Connection conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM tutorialjavacoches.coche where id < ? order by id desc limit 1");
			ps.setInt(1, cocheActual.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cocheResultado = getCocheFromResultSet(rs);		
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException | ImposibleConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cocheResultado;
	}
	
	
	
	
	/**
	 * 
	 * @return
	 */
	public static boolean guardarNuevoCoche (Coche coche) {
		boolean resultado = true;
		try {
			Connection conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = conn.prepareStatement(""
					+ "INSERT INTO tutorialjavacoches.coche values (?, ?, ?, ?, ?)");
			ps.setInt(1, getUltimoCoche().getId() + 1);
			ps.setInt(2, coche.getIdFabricante());
			ps.setString(3, coche.getBastidor());
			ps.setString(4, coche.getModelo());
			ps.setString(5, coche.getColor());
			int registrosAfectados = ps.executeUpdate();
			if (registrosAfectados != 1) {
				resultado = false;		
			}
			ps.close();
			conn.close();
		} catch (SQLException | ImposibleConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = false;
		}
		
		return resultado;
	}
	
	
	
	
	
	/**
	 * 
	 * @return
	 */
	public static boolean modificarCoche (Coche coche) {
		boolean resultado = true;
		try {
			Connection conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = conn.prepareStatement(""
					+ "UPDATE tutorialjavacoches.coche SET idFabricante = ?, bastidor = ?, modelo = ?, color = ? where id = ?");
			ps.setInt(1, coche.getIdFabricante());
			ps.setString(2, coche.getBastidor());
			ps.setString(3, coche.getModelo());
			ps.setString(4, coche.getColor());
			ps.setInt(5, coche.getId());
			int registrosAfectados = ps.executeUpdate();
			if (registrosAfectados != 1) {
				resultado = false;		
			}
			ps.close();
			conn.close();
		} catch (SQLException | ImposibleConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = false;
		}
		
		return resultado;
	}
	
	
	
	
	/**
	 * 
	 * @return
	 */
	public static boolean eliminarCoche (Coche coche) {
		boolean resultado = true;
		try {
			Connection conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = conn.prepareStatement(""
					+ "DELETE FROM tutorialjavacoches.coche where id = ?");
			ps.setInt(1, coche.getId());
			int registrosAfectados = ps.executeUpdate();
			if (registrosAfectados != 1) {
				resultado = false;		
			}
			ps.close();
			conn.close();
		} catch (SQLException | ImposibleConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultado = false;
		}
		
		return resultado;
	}
	
	
	
	
	
	private static Coche getCocheFromResultSet (ResultSet rs) {
		Coche coche = new Coche();
		
		try {
			coche.setId(rs.getInt("id"));
			coche.setIdFabricante(rs.getInt("idFabricante"));
			coche.setBastidor(rs.getString("bastidor"));
			coche.setModelo(rs.getString("modelo"));
			coche.setColor(rs.getString("color"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return coche;
	}
}
