package model_dao;

import java.util.List;

import model_entities.Department;
import model_entities.Seller;

public interface SellerDao {

    void insert(SellerDao obj);
    void update(SellerDao obj);
    void deleteById(Integer id);
    Seller findById(Integer id);
    List<Seller> findByDepartment(Department department);
    List<SellerDao> findAll();
    
}
