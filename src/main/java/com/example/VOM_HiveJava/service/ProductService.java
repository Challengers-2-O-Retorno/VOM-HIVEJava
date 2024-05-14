package com.example.VOM_HiveJava.service;

import com.example.VOM_HiveJava.dto.request.ProductRequest;
import com.example.VOM_HiveJava.dto.response.ProductResponse;
import com.example.VOM_HiveJava.entity.Product;
import com.example.VOM_HiveJava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.Collection;

public class ProductService implements ServiceDTO<Product, ProductRequest, ProductResponse> {

    @Autowired
    private ProductRepository repo;

    @Override
    public Product toEntity(ProductRequest r) {
        return Product.builder()
                .nm_product(r.nm_product())
                .category(r.category())
                .build();
    }

    @Override
    public ProductResponse toResponse(Product e) {
        return ProductResponse.builder()
                .id_product(e.getId_product())
                .nm_product(e.getNm_product())
                .category(e.getCategory())
                .build();
    }

    @Override
    public Collection<Product> findAll(Example<Product> example) {
        return repo.findAll( example );
    }

    @Override
    public Product findById(Long id) {
        return repo.findById( id ).orElse( null );
    }

    @Override
    public Product save(Product e) {
        return repo.save( e );
    }


}
