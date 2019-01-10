package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ETitulo;

public class ETituloDao {
	public void load(ETitulo to) {
		
		String sqlSelect = "SELECT u.user_id, u.fullname, u.cpf, u.birth_date, "
							+ "et.document_id, et.type_document_id, et.register, et.zone, et.section, et.city, et.uf, "
							+ "et.emission_at, et.validate_code FROM users u INNER JOIN etitulo et "
							+ "ON u.user_id = et.user_id WHERE u.user_id = ?;";
		
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = PiFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, to.getUser_id());
			try (ResultSet rs = stm.executeQuery();) {
				
				if (rs.next()) {
					to.setFullname(rs.getString("u.fullname"));
					to.setCpf(rs.getString("u.cpf"));
					to.setBirth_date(rs.getString("u.birth_date"));
					to.setDocument_id(rs.getInt("et.document_id"));
					to.setType(rs.getString("et.type_document_id").charAt(0));
					to.setRegister(rs.getString("et.register"));
					to.setZone(rs.getInt("et.zone"));
					to.setSection(rs.getString("et.section"));
					to.setCity(rs.getString("et.city"));
					to.setUf(rs.getString("et.uf"));
					to.setEmission_at(rs.getString("et.emission_at"));
					to.setValidate_code(rs.getString("et.validate_code"));
				}
				else {
					to.setUser_id(-1);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
	}
}
