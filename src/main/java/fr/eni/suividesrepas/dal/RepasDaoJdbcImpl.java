package fr.eni.suividesrepas.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.suividesrepas.bo.Aliment;
import fr.eni.suividesrepas.bo.Repas;
import fr.eni.suividesrepas.message.CodeErreur;
import fr.eni.suividesrepas.message.LecteurMessage;

public class RepasDaoJdbcImpl implements DAO<Repas>{
	
	@Override
	public void insert(Repas r) throws SQLException {
		String repasSql = "INSERT INTO repas(rDate, rTime) VALUES (?, ?)";
		String alimentSql = "INSERT INTO aliment(aliment, idRepas) VALUES (?, ?)";
		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(repasSql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
			 ps.setDate(1, r.getrDate());
			 ps.setTime(2, r.getrTime());
			 ps.executeUpdate();
			 
			 ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					r.setId(generatedKeys.getInt(1));
				}
		} catch (SQLException e) {
			e.printStackTrace();
			new Exception(LecteurMessage.getMessageErreur(CodeErreur.ERROR_INSERT));
		}
		
		for(Aliment a : r.getAliments()) {
			a.setIdRepas(r.getId());
			
			try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(alimentSql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
				 ps.setString(1, a.getAliment());
				 ps.setInt(2, a.getIdRepas());
				 ps.executeUpdate();
				 
				 ResultSet generatedKeys = ps.getGeneratedKeys();
					if (generatedKeys.next()) {
						a.setId(generatedKeys.getInt(1));
					}
			} catch (SQLException e) {
				e.printStackTrace();
				new Exception(LecteurMessage.getMessageErreur(CodeErreur.ERROR_INSERT));
			}
		}
		
	}

	@Override
	public List<Repas> selectAll() throws SQLException {
		String repasSql = "SELECT * FROM repas ORDER BY rDate DESC";
		String alimentSql = "SELECT * FROM aliment WHERE idRepas = ?";
		List<Repas> repas = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(repasSql)) {
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Repas r = new Repas(rs.getInt("id"), rs.getDate("rDate"), rs.getTime("rTime"));
				repas.add(r);
			}
			
			for (Repas r : repas) {
				try(Connection cnx2 = ConnectionProvider.getConnection(); PreparedStatement ps2 = cnx.prepareStatement(alimentSql)) {
					ps2.setInt(1, r.getId());
					
					ResultSet rs2 = ps2.executeQuery();
					
					while (rs2.next()) {
						r.addAliments(new Aliment(rs2.getString("aliment")));
					}
					
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			new Exception(LecteurMessage.getMessageErreur(CodeErreur.ERROR_SELECT));
		}
		
		return repas;
	}

}
