package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cnh;

public class CnhDao {
	public void load(Cnh to) {
		
		String sqlSelect = "SELECT u.user_id, u.fullname, u.cpf, u.birth_date, "
							+ "cnh.document_id, cnh.type_document_id, cnh.register, cnh.category, cnh.city, cnh.uf,"
							+ "cnh.emission_at, cnh.due_date_at, cnh.validate_code FROM users u INNER JOIN cnh cnh "
							+ "ON u.user_id = cnh.user_id WHERE u.user_id = ?";
		
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = PiFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, to.getUser_id());
			try (ResultSet rs = stm.executeQuery();) {
				
				if (rs.next()) {
					to.setFullname(rs.getString("u.fullname"));
					to.setCpf(rs.getString("u.cpf"));
					to.setBirth_date(rs.getString("u.birth_date"));
					to.setDocument_id(rs.getInt("cnh.document_id"));
					to.setType(rs.getString("cnh.type_document_id").charAt(0));
					to.setRegister(rs.getString("cnh.register"));
					to.setCategory(rs.getString("cnh.category"));
					to.setCity(rs.getString("cnh.city"));
					to.setUf(rs.getString("cnh.uf"));
					to.setEmission_at(rs.getString("cnh.emission_at"));
					to.setDue_date_at(rs.getString("cnh.due_date_at"));
					to.setValidate_code(rs.getString("cnh.validate_code"));
					
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
