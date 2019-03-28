import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControladorBBDDCoche {
	
	public static Coche getPrimerCoche() {
		
		Coche coche = null;
		
		try {
			Connection conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM tutorialjavacoches.coche limit 1");
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				coche = getCocheFromResultSet(rs);
			}
			
		} catch (SQLException | ImposibleConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return coche;
	}
	
	/**
	 * 
	 * @param rs
	 * @return
	 */
	private static Coche getCocheFromResultSet (ResultSet rs) {
		
		Coche coche = new Coche();
		
		try {
			coche.setId(rs.getInt("id"));
			coche.setIdfabricante(rs.getInt("idFabricante"));
			coche.setBastidor(rs.getString("bastidor"));
			coche.setModelo(rs.getString("modelo"));
			coche.setBastidor(rs.getString("bastidor"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return coche;
		
	}
	
}
