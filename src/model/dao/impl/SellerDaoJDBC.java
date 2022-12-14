package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			st.setInt(1, id);
			
			// o result set recebe um dado do banco em forma de tabela
			// é preciso destrinchar os dados e instancaiar objetos equivalentes
			rs = st.executeQuery();
			
			// o rs vem na posição 0, onde não há dados. 
			if(rs.next()) {
				//caso tenha dados no resultado
				Department dep = instatiateDepartment(rs);
				Seller seller = instantiateSeller(rs, dep);
				
				return seller;
			}
			
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatment(st);
			DB.closeResultSet(rs);
		}
		
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getNString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartmente(dep);
		return seller;
	}

	private Department instatiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name");
			
			// o result set recebe um dado do banco em forma de tabela
			// é preciso destrinchar os dados e instancaiar objetos equivalentes
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			
			/*criando um map (chave/valor) para aramazenar id e departamento
			 */
			Map<Integer, Department> map = new HashMap<>();
			
			// o rs vem na posição 0, onde não há dados. 
			while(rs.next()) {
				//caso tenha dados no resultado
				
				//verificar se existe o departamento no map a partir do id(chave)
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				//caso não exista crio o registro de chave(id), valor(dep)
				if(dep == null) {
					dep = instatiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller seller = instantiateSeller(rs, dep);
				
				list.add(seller);
			}
			
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatment(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartament(Department department) {
			
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");
			
			st.setInt(1, department.getId());
			
			// o result set recebe um dado do banco em forma de tabela
			// é preciso destrinchar os dados e instancaiar objetos equivalentes
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			
			/*criando um map (chave/valor) para aramazenar id e departamento
			 */
			Map<Integer, Department> map = new HashMap<>();
			
			// o rs vem na posição 0, onde não há dados. 
			while(rs.next()) {
				//caso tenha dados no resultado
				
				//verificar se existe o departamento no map a partir do id(chave)
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				//caso não exista crio o registro de chave(id), valor(dep)
				if(dep == null) {
					dep = instatiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller seller = instantiateSeller(rs, dep);
				
				list.add(seller);
			}
			
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatment(st);
			DB.closeResultSet(rs);
		}
	}

}
