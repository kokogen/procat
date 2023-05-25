package org.koko.procat.service;

import org.koko.procat.entity.Product;
import org.koko.procat.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product findById(Long id){
        Product product = repository.findById(id).get();
        //product.getCatalogues().size();
        return product;
    }

    public List<Product> findAll(){
        List<Product> res = repository.findAll();
        /*
        res.forEach(x -> {
            x.getCatalogues().iterator();
            x.getManufacturer().getProducts().iterator();
        });
         */
        return res;
    }
}
