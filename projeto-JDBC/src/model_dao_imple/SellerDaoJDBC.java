package model_dao_imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.Statement;

import model_dao.SellerDao;
import model_entities.Department;
import model_entities.Seller;
import java.sql.*;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement st = null;

        try{
  
            st = conn.prepareStatement(
                "INSERT INTO seller "
                + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                + "VALUES "
                + "(?, ?, ?, ?, ?)",
                java.sql.Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail()); 
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));  
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId()); 

            int rowsEffected = st.executeUpdate();

            if(rowsEffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                try {
                    if (rs != null)
                        rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                throw new SQLException("Erro inesperado, nenhuma linha foi afetada");
            }

        }catch(SQLException e){
            e.getSQLState();
            e.printStackTrace();
        }
        finally{

            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void update(Seller obj) {
        PreparedStatement st = null;

        try{
  
            st = conn.prepareStatement(
                "UPDATE seller "
                +"SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                + "WHERE id = ?",
                java.sql.Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail()); 
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));  
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId()); 
            st.setInt(6, obj.getId());

           st.executeUpdate();

        }catch(SQLException e){
            e.getSQLState();
            e.printStackTrace();
        }
        finally{

            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement st = null;

        try{
  
            st = conn.prepareStatement(
                "DELETE FROM seller "
                + "WHERE id = ?",
                java.sql.Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, id);

           st.executeUpdate();
           System.out.println("Delete Completed");

        }catch(SQLException e){
            e.getSQLState();
            e.printStackTrace();
        }
        finally{

            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery(); // executa o comando

            if (rs.next()) { // textando se veio algum resultado
                Department dep = InstatiateDepartment(rs);
                Seller obj = InstantiateSeller(rs, dep);
                return obj;
            }

            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public List<Seller> findAll() {

        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "ORDER BY Name");

            rs = st.executeQuery(); // executa o comando

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>(); // lista de departamentos já usados

            while (rs.next()) { // percorrendo todas as linhas retornadas
                Department dep = map.get(rs.getInt("DepartmentId"));
                
                //if para varios sellers apontarem para o mesmo departamento (do certo id) na memoria
                if (dep == null) {
                    dep = InstatiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                
                Seller obj = InstantiateSeller(rs, dep);
                list.add(obj);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "WHERE DepartmentId = ? "
                            + "ORDER BY Name");

            st.setInt(1, department.getId());
            rs = st.executeQuery(); // executa o comando

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>(); // lista de departamentos já usados

            while (rs.next()) { // percorrendo todas as linhas retornadas
                Department dep = map.get(rs.getInt("DepartmentId"));
                
                //if para varios sellers apontarem para o mesmo departamento (do certo id) na memoria
                if (dep == null) {
                    dep = InstatiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                
                Seller obj = InstantiateSeller(rs, dep);
                list.add(obj);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Department InstatiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    private Seller InstantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartment(dep);
        return obj;
    }

}
