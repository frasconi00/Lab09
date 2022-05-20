package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public Map<Integer,Country> loadAllCountries() {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		Map<Integer,Country> result = new HashMap<Integer, Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.put(new Integer(rs.getInt("CCode")) ,new Country(rs.getInt("CCode"),rs.getString("StateNme")));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno) {

		String sql="SELECT * "
				+ "FROM contiguity "
				+ "WHERE YEAR <= ? AND conttype = 1";
		List<Border> result = new ArrayList<Border>();
		Connection conn = ConnectDB.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				Border border = new Border(res.getInt("state1no"), res.getInt("state2no"), res.getInt("year"));
				boolean trovato=false;
				for(Border b : result) {
					if(   (b.getState1no()==border.getState1no() && b.getState2no()==border.getState2no())
							|| (b.getState1no()==border.getState2no() && b.getState2no()==border.getState1no()) ) {
						trovato=true;
					}
				}
				if(!trovato)
					result.add(border);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al databas");
			throw new RuntimeException("Error Connection Database");
		}
	}
}
