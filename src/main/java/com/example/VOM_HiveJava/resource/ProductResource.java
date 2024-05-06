package com.example.VOM_HiveJava.resource;

import com.example.VOM_HiveJava.dto.request.ProductRequest;
import com.example.VOM_HiveJava.dto.response.ProductResponse;
import com.example.VOM_HiveJava.entity.Product;
import com.example.VOM_HiveJava.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Product save(@RequestBody Product p) {
        return repo.save( p );
    }

}