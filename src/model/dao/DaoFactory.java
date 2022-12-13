package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	/* Classe responsável por fazer a aplicação de dependencia e implementar 
	 * as classes de acesso ao banco de dados
	 */
	 
	public static SellerDao createSellerDao(){
		return new SellerDaoJDBC();
	}
	
}
