package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("Inserindo novo departamento");
		Department dep = new Department(null, "Teste");
		//departmentDao.insert(dep);

		System.out.println("\nAtualizando departamento");
		Department upDep = new Department(6, "teste");
		upDep.setName("Novo teste UP");
		departmentDao.update(upDep);
		
		System.out.println("\nDeletando department");
		int id = 8;
		//departmentDao.deleteById(id);
		
		System.out.println("\nBuscando departamento por id");
		Department dep2 = departmentDao.findById(3);
		System.out.println(dep2);
		
		System.out.println("\nBuscando todos os departamentos");
		List<Department> list = departmentDao.findAll();
		
		for(Department dep3 : list) {
			System.out.println(dep3);
		}
		
	}

}
