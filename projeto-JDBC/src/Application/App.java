package Application;

import db.DB;
import model_dao.DaoFactory;
import model_dao.DepartmentDao;
import model_dao.SellerDao;
import model_entities.Department;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.security.spec.ECFieldF2m;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import model_entities.Seller;

public class App {
    
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(3);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("\n======== seller FindById =======\n"); //retorna um objeto seller

        System.out.println(seller);

        System.out.println("\n======== seller FindByDeparment =======\n"); //retorna uma lista de sellers

        Department dep = new Department(6, null);
        List<Seller> list = sellerDao.findByDepartment(dep);
        for(Seller a : list){
            System.out.println(a);
        }

        System.out.println("\n======== seller findAll =======\n"); //retorna todos os sellers
        List<Seller> list2 = sellerDao.findAll();
        for(Seller a : list2){
            System.out.println(a);
        }

        departmentDao.deleteById(2);
  

    }

}
