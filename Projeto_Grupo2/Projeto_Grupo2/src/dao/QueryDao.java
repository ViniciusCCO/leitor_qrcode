package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Cnh;
import model.Ctps;
import model.ETitulo;

public class QueryDao {
	
	public void create() {
		stmCreate("", -1);
	}
	
	public void create(Cnh to) {
		stmCreate("cnh_id", to.getDocument_id());
	}
	
	public void create(Ctps to) {
		stmCreate("ctps_id", to.getDocument_id());
	}
	
	public void create(ETitulo to) {		
		stmCreate("etitulo_id", to.getDocument_id());
	}
	
	public void stmCreate(String sqlDocument, int document_id) {
		String stmString = "";
		String result = "Invalido";
		
		if(!sqlDocument.equals("")) {
			sqlDocument = ", " + sqlDocument;
			stmString = ", ?";
			result = "Valido";
		}
		
		String sqlInsert = "INSERT INTO query (result" + sqlDocument + ") VALUES (?" + stmString + ")";
		
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = PiFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			
			stm.setString(1, result);
			
			if(!stmString.equals(""))
				stm.setInt(2, document_id);
			
			stm.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	} 
}
