package Application;

import db.DB;
import model_dao.DaoFactory;
import model_dao.SellerDao;
import model_entities.Department;
import java.sql.Statement;
import java.security.spec.ECFieldF2m;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model_entities.Seller;

public class App {
    
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);
    
     
    }

}
