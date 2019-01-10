package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Ctps;

public class CtpsDao {
	public void load(Ctps to) {
		
		String sqlSelect = "SELECT u.user_id, u.fullname, u.cpf, u.birth_date, "
							+ "ctps.document_id, ctps.type_document_id, ctps.number, ctps.serie, ctps.birth_city, ctps.birth_uf, "
							+ "ctps.emission_at, ctps.validate_code FROM users u INNER JOIN ctps ctps "
							+ "ON u.user_id = ctps.user_id WHERE u.user_id = ?;";
		
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = PiFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, to.getUser_id());
			try (ResultSet rs = stm.executeQuery();) {
				
				if (rs.next()) {
					to.setFullname(rs.getString("u.fullname"));
					to.setCpf(rs.getString("u.cpf"));
					to.setBirth_date(rs.getString("u.birth_date"));
					to.setDocument_id(rs.getInt("ctps.document_id"));
					to.setType(rs.getString("ctps.type_document_id").charAt(0));
					to.setNumber(rs.getString("ctps.number"));
					to.setSerie(rs.getString("ctps.serie"));
					to.setCity(rs.getString("ctps.birth_city"));
					to.setUf(rs.getString("ctps.birth_uf"));
					to.setEmission_at(rs.getString("ctps.emission_at"));
					to.setValidate_code(rs.getString("ctps.validate_code"));
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
