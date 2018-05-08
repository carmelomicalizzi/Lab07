package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;
import it.polito.tdp.poweroutages.model.PowerOutageIdMap;

public class PowerOutageDAO {

	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<PowerOutage> getPowerOutageFromNerc(Nerc n, PowerOutageIdMap powerOutageMap) {

		String sql = "SELECT p.id, p.date_event_began, p.date_event_finished, p.customers_affected " + 
				"FROM poweroutages p JOIN nerc n ON n.id = p.nerc_id " + 
				"WHERE n.value = ? " + 
				"ORDER BY p.date_event_began asc";
		
		List<PowerOutage> powerOutageList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, n.getValue());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutage p = new PowerOutage(res.getInt("p.id"), res.getTimestamp("p.date_event_began"), res.getTimestamp("p.date_event_finished"), res.getInt("p.customers_affected"));
				powerOutageList.add(powerOutageMap.get(p));
				n.getPowerOutages().add(powerOutageMap.get(p));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return powerOutageList;
	}
	
	public List<PowerOutage> getAllPowerOutage(PowerOutageIdMap powerOutageMap) {

		String sql = "SELECT p.id, p.date_event_began, p.date_event_finished, p.customers_affected " + 
				"FROM poweroutages p JOIN nerc n ON n.id = p.nerc_id " +  
				"ORDER BY p.date_event_began asc";
		
		List<PowerOutage> powerOutageList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutage p = new PowerOutage(res.getInt("p.id"), res.getTimestamp("p.date_event_began"), res.getTimestamp("p.date_event_finished"), res.getInt("p.customers_affected"));
				powerOutageList.add(powerOutageMap.get(p));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return powerOutageList;
	}

}
