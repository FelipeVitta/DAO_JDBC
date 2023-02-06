package model_dao_imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.Statement;

import model_dao.DepartmentDao;
import model_entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO department "
                            + "(Name) "
                            + "VALUES (?)",
                    java.sql.Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());

            int rows = st.executeUpdate();

            if (rows > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("Updated! " + obj.getName());
                }
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        finally {

            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE department " +
                            "SET Name = ? " +
                            "WHERE id = ?",

                    java.sql.Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            int rows = st.executeUpdate();

            if (rows > 0)
                System.out.println("Updated! " + obj.getId() + " " + obj.getName());

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }

        finally {

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
        PreparedStatement st2 = null;
        ResultSet rs = null;

        try{
            st2 = conn.prepareStatement(
            "SELECT seller.Name FROM seller " +
            "WHERE seller.DepartmentId = ?"
            );

            st2.setInt(1,id);
            rs = st2.executeQuery();
           
            if(rs.next()){
                System.out.println("Someone is still in this department");              
            }else{
                st = conn.prepareStatement(
                    "DELETE FROM department " +
                    "WHERE department.Id = ?"
                );
    
                st.setInt(1, id);
    
                int rows = st.executeUpdate();
    
                if(rows > 0){
                    System.out.println("Department " + id + " has been deleted");
                }
            }

        }catch(Exception e){
            e.getCause();
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
    public Department findById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Department> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
