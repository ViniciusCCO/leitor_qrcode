package service;

import Utilitary.Metodo;
import dao.ETituloDao;
import dao.QueryDao;
import model.ETitulo;

public class ETituloService {
	ETituloDao dao;
	QueryDao qdao;
	
	public ETituloService() {
		dao = new ETituloDao();
		qdao = new QueryDao();
	}
	
	public boolean load(ETitulo to, ETitulo base) {
		boolean validate = true;
		try {
			dao.load(to);
			if(to.getDocument_id() == -1 || !to.equals(base)) {
				validate = false;
				Metodo.invalidDocumentError();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			Metodo.invalidDocumentError();
			validate = false;
		}
		
		if(validate)
			qdao.create(to);
		else
			qdao.create();
		
		return validate;
	}
}
