package service;

import Utilitary.Metodo;
import dao.CtpsDao;
import dao.QueryDao;
import model.Ctps;

public class CtpsService {
	CtpsDao dao;
	QueryDao qdao;
	
	public CtpsService() {
		dao = new CtpsDao();
		qdao = new QueryDao();
	}
	
	public boolean load(Ctps to, Ctps base) {
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
