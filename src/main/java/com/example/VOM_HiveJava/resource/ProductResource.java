package com.example.VOM_HiveJava.resource;

import com.example.VOM_HiveJava.entity.Product;
import com.example.VOM_HiveJava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductResource {

    @Autowired
    private ProductRepository repo;


    @GetMapping
    public List<Product> findAll() {
        return repo.findAll();

    }
    @GetMapping(value = "/{id}")
    public Product findById(@PathVariable Long id) {
        return repo.findById( id ).orElse( null );
    }

}