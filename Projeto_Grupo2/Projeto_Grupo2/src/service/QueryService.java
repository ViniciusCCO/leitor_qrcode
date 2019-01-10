package service;

import dao.QueryDao;
import model.Cnh;
import model.Ctps;
import model.ETitulo;

public class QueryService {
	QueryDao dao;
	
	public QueryService() {
		dao = new QueryDao();
	}
	
	//Invalidos
	public void create() {
		dao.create();
	}
	
	//Cnh - Validos
	public void create(Cnh to, Cnh base) {
		if(to.equals(base))
			dao.create(to);
	}
	
	//Ctps - Validos
	public void create(Ctps to, Ctps base) {
		if(to.equals(base))
			dao.create(to);
	}
	
	//ETitulo - Validos
	public void create(ETitulo to, ETitulo base) {
		if(to.equals(base))
			dao.create(to);
	}
}
