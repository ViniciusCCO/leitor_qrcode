package service;

import Utilitary.Metodo;
import dao.CnhDao;
import dao.QueryDao;
import model.Cnh;

public class CnhService {
	CnhDao dao;
	QueryDao qdao;
	
	public CnhService() {
		dao = new CnhDao();
		qdao = new QueryDao();
	}
	
	public boolean load(Cnh to, Cnh base) {
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
