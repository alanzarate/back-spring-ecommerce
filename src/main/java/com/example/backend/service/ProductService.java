package com.example.backend.service;

import com.example.backend.dao.ProductDao;
import com.example.backend.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public Product addNewProduct(Product product){
        return productDao.save(product);
        // if id is present in product, is an update, else is a create
    }

    public List<Product> getAllProducts(){
        return (List<Product>) productDao.findAll();
    }

    public void deleteProductsDetails(int productId){
        productDao.deleteById(productId);
    }

    public Product getProductDetailsById(int productId){
        return productDao.findById(productId).get();
    }

}
