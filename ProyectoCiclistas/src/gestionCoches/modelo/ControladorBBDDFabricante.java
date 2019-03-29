package gestionCoches.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gestionCoches.modelo.entidades.Coche;
import gestionCoches.modelo.entidades.Fabricante;


public class ControladorBBDDFabricante {

	
	/**
	 * 
	 * @return
	 */
	public static List<Fabricante> getTodosFabricantes () {
		List<Fabricante> resultado = new ArrayList<Fabricante>();
		try {
			Connection conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM tutorialjavacoches.fabricante order by nombre");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultado.add(getFabricanteFromResultSet(rs));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException | ImposibleConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	
	/**
	 * 
	 * @param rs
	 * @return
	 */
	private static Fabricante getFabricanteFromResultSet (ResultSet rs) {
		Fabricante fabricante = new Fabricante();
		
		try {
			fabricante.setId(rs.getInt("id"));
			fabricante.setCif(rs.getString("cif"));
			fabricante.setNombre(rs.getString("nombre"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fabricante;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Fabricante getPrimerFabricante() {
		
		Fabricante fab = new Fabricante();
		
		try {
			Connection conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM tutorialjavacoches.fabricante order by id limit 1");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				fab = getFabricanteFromResultSet(rs);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException | ImposibleConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fab;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static Fabricante getSiguienteFabricante(Fabricante fabActual) {
		Fabricante fab = null;
		try {
			Connection conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM tutorialjavacoches.fabricante where id > ? order by id limit 1");
			ps.setInt(1, fabActual.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				fab = getFabricanteFromResultSet(rs);		
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException | ImposibleConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fab;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Fabricante getAnteriorFabricante(Fabricante fabActual) {
		Fabricante fab = null;
		try {
			Connection conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM tutorialjavacoches.fabricante where id < ? order by id DESC limit 1");
			ps.setInt(1, fabActual.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				fab = getFabricanteFromResultSet(rs);		
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException | ImposibleConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fab;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Fabricante getUltimoFabricante() {
		
		Fabricante fab = new Fabricante();
		
		try {
			Connection conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM tutorialjavacoches.fabricante order by id DESC limit 1");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				fab = getFabricanteFromResultSet(rs);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException | ImposibleConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fab;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static boolean guardarNuevoFabricante (Fabricante fab) {
		boolean resultado = true;
		try {
			Connection conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = conn.prepareStatement(""
					+ "INSERT INTO tutorialjavacoches.fabricante values (?, ?, ?)");
			ps.setInt(1, getUltimoFabricante().getId() + 1);
			ps.setString(2, fab.getCif());
			ps.setString(3, fab.getNombre());

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
	public static boolean modificarFabricante (Fabricante fab) {
		boolean resultado = true;
		try {
			Connection conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = conn.prepareStatement(""
					+ "UPDATE tutorialjavacoches.fabricante SET id = ?, cif = ?, nombre = ? where id = ?");
			ps.setInt(1, fab.getId());
			ps.setString(2, fab.getCif());
			ps.setString(3, fab.getNombre());
			ps.setInt(4, fab.getId());

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
	public static boolean eliminarFabricante (Fabricante fab) {
		boolean resultado = true;
		try {
			Connection conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = conn.prepareStatement(""
					+ "DELETE FROM tutorialjavacoches.fabricante where id = ?");
			ps.setInt(1, fab.getId());
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
}
