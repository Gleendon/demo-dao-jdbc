package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		System.out.println("Testando conexão e buscando vendendor por ID");
		//cria a conexão e instancia o sellerDaoJDBC
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller seller = sellerDao.findById(3);
		
		System.out.println(seller);
		
		System.out.println("\n Procurando vendedor pelo id do departamento");
		Department dep = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartament(dep);
		
		for(Seller seller2 : list) {
			
			System.out.println(seller2);
		}
	
		System.out.println("\n Buscando todos os vendedores");
		List<Seller> list3 = sellerDao.findAll();
		
		for(Seller seller2 : list3) {
			
			System.out.println(seller2);
		}
	}

}
