package model_dao;

import java.util.List;

import model_entities.Seller;

public interface SellerDao {

    void insert(SellerDao obj);
    void update(SellerDao obj);
    void deleteById(Integer id);
    SellerDao findById(Integer id);
    List<SellerDao> findAll();
    
}
