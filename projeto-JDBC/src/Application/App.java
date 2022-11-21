package Application;

import db.DB;
import model_dao.DaoFactory;
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

        System.out.println("\n======== seller FindById =======\n"); //retorna um objeto seller

        System.out.println(seller);

        System.out.println("\n======== seller FindByDeparment =======\n"); //retorna uma lista de sellers

        Department dep = new Department(1, null);
        List<Seller> list = sellerDao.findByDepartment(dep);
        for(Seller a : list){
            System.out.println(a);
        }

        System.out.println("\n======== seller findAll =======\n"); //retorna todos os sellers
        List<Seller> list2 = sellerDao.findAll();
        for(Seller a : list2){
            System.out.println(a);
        }

        System.out.println("\n======== insert seller =======\n"); //insere um novo seller
        Seller seller2 = new Seller(null,"Gabriel Coelho","coelho@gmail.com",new Date(0), 3000.0, dep);
        // sellerDao.insert(seller2);
        // System.out.println("Inserido! Novo id = " + seller2.getId());

        System.out.println("\n======== sellet update =======\n"); //atualiza um seller ja existente
        // seller2 = sellerDao.findById(4);
        // seller2.setName("Martolas");
        // sellerDao.update(seller2);
        // System.out.println("update completed");

        System.out.println("\n======== delete seller =======\n"); //atualiza um seller ja existente

        // sellerDao.deleteById(3);



    }

}
