package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
//		Department dp = new Department(6, "boobks");
//		
//		Seller seller = new Seller(20, "Valleria", "Valleria@gmail", new Date(), 5000.0, dp);
//		
		//criando a conexão
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = sellerDao.findById(3);
		
		System.out.println(seller);
	}

}
